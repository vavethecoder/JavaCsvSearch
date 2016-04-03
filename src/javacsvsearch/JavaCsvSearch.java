package javacsvsearch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Jiss
 */
public class JavaCsvSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer choice;
        String product, user;
        Scanner scan = new Scanner(System.in);
        System.out.println("Search by product: 1");
        System.out.println("Search by user's last name: 2");
        System.out.print("Enter your search choice: ");
        choice = scan.nextInt();
        scan.nextLine();
        HashMap<Integer, Map<String, String>> list = arrangeList();
        switch (choice) {
            case 1:
            case 2:
                System.out.print("Enter your product name: ");
                product = scan.nextLine();
                System.out.println("Search result for " + product);
                HashMap<Integer, Map<String, String>> result = search(list, product, choice);
                System.out.println(result);
                break;
            default:
                System.out.println("You entered wrong choice.");
                break;
        }

    }

    public static HashMap arrangeList() {
        BufferedReader bufferReader = null;
        String splitBy = ",";

        Integer count = 0;
        HashMap<Integer, Map<String, String>> content = new HashMap<Integer, Map<String, String>>();

        try {
            bufferReader = new BufferedReader(new FileReader("Data - Sheet1.csv"));
            String[] heading = bufferReader.readLine().split(splitBy);
            String currentLine;
            while ((currentLine = bufferReader.readLine()) != null) {
                String[] Singlecontent = currentLine.split(splitBy);
                HashMap<String, String> contentMap = new HashMap<String, String>();
                for (int i = 0; i < Singlecontent.length; i++) {
                    contentMap.put(heading[i], Singlecontent[i]);
                }
                content.put(count, contentMap);
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    public static HashMap<Integer, Map<String, String>> search(HashMap<Integer, Map<String, String>> list, String term, Integer type) {
        HashMap<Integer, Map<String, String>> result = new HashMap<Integer, Map<String, String>>();
        int listSize = list.size();
        int j = 0;
        for (int i = 0; i < listSize; i++) {
            if (type == 1 && list.get(i).get("productname").contains(term)) {
                result.put(j, list.get(i));
            } else if (type == 2 && list.get(i).get("lastname").contains(term)) {
                result.put(j, list.get(i));
            }
            j++;
        }
        return result;
    }

}
