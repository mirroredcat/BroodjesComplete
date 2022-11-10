package be.abis.menuapi.repository;



import be.abis.menuapi.exceptions.SandwichAlreadyExistsException;
import be.abis.menuapi.exceptions.SandwichNotFoundException;
import be.abis.menuapi.model.Sandwich;
import be.abis.menuapi.model.SandwichCompany;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FileSandwichRepository implements SandwichRepository{

    private List<Sandwich> sandwichList = new ArrayList<>();
    private static final String fileLoc = "/temp/javacourses/Broodjes/sandwiches.csv";

    @PostConstruct
    public void  init(){
        this.readFile();
    }

    public void readFile() {

        List<String> stringLines = null;
        try {
            stringLines = Files.readAllLines(Paths.get(fileLoc), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String s : stringLines) {
            String[] vals = s.split(";");
            if (!vals[0].equals("")){
                Sandwich sa = new Sandwich();
                sa.setId(!vals[0].equals("null") ? Integer.valueOf(vals[0]):null);
                sa.setSandwichName(!vals[1].equals("null") ? vals[1]:null);
                if(!vals[2].equals("null")) {
                    switch (vals[2]) {
                        case "Pinky's":
                            sa.setSandwichCompany(SandwichCompany.PINKYS);break;
                        case "Vleugels":
                            sa.setSandwichCompany(SandwichCompany.VLEUGELS);break;
                    }
                }
                sa.setCategory(!vals[3].equals("null") ? vals[3]:null);
                sa.setPrice(!vals[4].equals("null") ? Double.valueOf(vals[4]):null);
                sa.setIngredients(vals[5]);

                sandwichList.add(sa);
            }
        }
    }

    public List<Sandwich> findSandwichesByRestaurant(String name) {
        return getSandwichList().stream()
                .filter(s -> s.getSandwichCompany().getCompanyName().equals(name))
                .collect(Collectors.toList());
    }

    public Sandwich findSandwich(int id) throws SandwichNotFoundException {
        return getSandwichList().stream()
                .filter(s -> s.getId() == id)
                .findFirst().orElseThrow(()->new SandwichNotFoundException("This sandwich does not exist"));
    }

    public Sandwich findSandwich(String sandwichName, String companyName) throws SandwichNotFoundException {
        return getSandwichList().stream()
                .filter(s -> (s.getSandwichName().equals(sandwichName) && s.getSandwichCompany().getCompanyName().equals(companyName)))
                .findFirst().orElseThrow(()-> new SandwichNotFoundException("This sandwich does not exist"));
    }

    public List<Sandwich> getSandwichList() {
        return sandwichList;
    }

    private void setSandwichList(ArrayList<Sandwich> sandwichList) {
        this.sandwichList = sandwichList;
    }


    public void addSandwich(Sandwich sandwich) throws SandwichAlreadyExistsException {
        Sandwich s = null;
        try {
            s = this.findSandwich(sandwich.getId());
        } catch (SandwichNotFoundException ignored) {

        }
        if (s==null){
            sandwichList.add(sandwich);
            this.writeToFile();
        } else {
            throw new SandwichAlreadyExistsException("A sandwich with id " + sandwich.getId() + " already exists");
        }

    }

    public void deleteSandwich(int id) throws SandwichNotFoundException {
        Sandwich foundS = this.findSandwich(id);
        sandwichList.remove(foundS);
        this.writeToFile();
    }

    private StringBuilder parseSandwich(Sandwich sandwich){
        StringBuilder sb = new StringBuilder();
        sb.append(sandwich.getId()).append(";")
                .append(sandwich.getSandwichName()).append(";")
                .append(sandwich.getSandwichCompany().getCompanyName()).append(";")
                .append(sandwich.getCategory()).append(";")
                .append(sandwich.getPrice()).append(";")
                .append(sandwich.getIngredients());
        return sb;
    }

    private void writeToFile() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(fileLoc));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Sandwich s : sandwichList) {
            StringBuilder sb = this.parseSandwich(s);
            pw.println(sb);
        }
        pw.close();
    }

    public void updateSandwichPrice(int id, Double newPrice) throws SandwichNotFoundException {
        Sandwich foundS = this.findSandwich(id);
        foundS.setPrice(newPrice);
        this.writeToFile();
    }

    public void updateSandwichIngredients(int id, String newIngredients) throws SandwichNotFoundException {
        Sandwich foundS = this.findSandwich(id);
        foundS.setIngredients(newIngredients);
        this.writeToFile();
    }


}
