package be.abis.superdupersandwichorder.repository;


import be.abis.superdupersandwichorder.exceptions.DayOrderNotFoundException;
import be.abis.superdupersandwichorder.exceptions.OrderAlreadyRegisteredException;
import be.abis.superdupersandwichorder.model.SandwichCompany;
import be.abis.superdupersandwichorder.model.StoredDayOrder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class FileOrderHistoryRepository implements OrderHistoryRepository{

    private List<StoredDayOrder> dayOrderHistoryList;
    private String fileLocation = "/temp/javacourses/Broodjes/orderhistory.csv";

    @PostConstruct
    public void init() {
        this.readFile();
    }

    public FileOrderHistoryRepository() {
        dayOrderHistoryList = new ArrayList<>();
    }

    public void readFile() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileLocation));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String line : lines) {
            StoredDayOrder d = convertToOrderObj(line);
            dayOrderHistoryList.add(d);
        }

    }


    @Override
    public void addDayOrder(StoredDayOrder storedDayOrder) throws IOException, OrderAlreadyRegisteredException {

        Iterator<StoredDayOrder> orderHistoryIterator = dayOrderHistoryList.iterator();
        boolean orderAlreadyRegistered = false;
        while (orderHistoryIterator.hasNext()) {
            StoredDayOrder order = orderHistoryIterator.next();
            if (order.getId()==storedDayOrder.getId() && order.getSessionID()== storedDayOrder.getSessionID()) {
                orderAlreadyRegistered = true;
            }
        } if (orderAlreadyRegistered) {
            throw new OrderAlreadyRegisteredException("Order already registered");
        } else {
            dayOrderHistoryList.add(storedDayOrder);
            this.writeToFile();
        }

    }

    @Override
    public void deleteDayOrder(StoredDayOrder storedDayOrder) throws IOException {
        dayOrderHistoryList.remove(storedDayOrder);
        this.writeToFile();

    }

    @Override
    public StoredDayOrder findDayOrderByDate(LocalDate localDate) throws DayOrderNotFoundException {
        return dayOrderHistoryList.stream()
                .filter(storedDayOrder -> storedDayOrder.getDate().equals(localDate))
                .findFirst().orElseThrow(()-> new DayOrderNotFoundException("No order found with this date"));
    }

    @Override
    public StoredDayOrder findDayOrderByID(int id) throws DayOrderNotFoundException {
        return dayOrderHistoryList.stream()
                .filter(storedDayOrder -> storedDayOrder.getId()==id)
                .findFirst().orElseThrow(()-> new DayOrderNotFoundException("No order found with this date"));
    }

    @Override
    public StoredDayOrder convertToOrderObj(String attr) {
        String[] vals = attr.split(";");
        StoredDayOrder d = null;
        if (!vals[0].equals("")) {
            d = new StoredDayOrder();
            d.setId(!vals[0].equals("null") ? Integer.valueOf(vals[0]):null);
            d.setDate(!vals[1].equals("null") ? convertDateToObj(vals[1]) : null);
            d.setOrderListSize(!vals[2].equals("null") ? Integer.valueOf(vals[2]) : null);
            if (!vals[3].equals("null")) {
                switch (vals[3]) {
                    case "Pinky's":
                        d.setSandwichCompany(SandwichCompany.PINKYS); break;
                    case "Vleugels":
                        d.setSandwichCompany(SandwichCompany.VLEUGELS); break;
                    default:
                        d.setSandwichCompany(null);
                }
            }
            d.setDayTotal(!vals[4].equals("null") ? Double.valueOf(vals[4]) : null);
            d.setSessionID(!vals[5].equals("null") ? Integer.valueOf(vals[5]) : null);
        }
        return d;
    }

    @Override
    public LocalDate convertDateToObj(String attr) {
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(attr, dtm);
    }

    private void writeToFile() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(fileLocation));

        for (StoredDayOrder dayOrder : dayOrderHistoryList) {
            StringBuilder sb = this.parseStoredDayOrder(dayOrder);
            pw.println(sb);
        }
        pw.close();
    }

    private StringBuilder parseStoredDayOrder(StoredDayOrder storedDayOrder) {
        StringBuilder sb = new StringBuilder();
        sb.append(storedDayOrder.getId()).append(";")
                .append(storedDayOrder.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).append(";")
                .append(storedDayOrder.getOrderListSize()).append(";")
                .append(storedDayOrder.getSandwichCompany().getCompanyName()).append(";")
                .append(storedDayOrder.getDayTotal())
                .append(storedDayOrder.getSessionID());
        return sb;
    }

    @Override
    public List<StoredDayOrder> getDayOrderHistoryList() {
        return dayOrderHistoryList;
    }
}
