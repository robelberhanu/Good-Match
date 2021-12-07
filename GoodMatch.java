import java.util.*;
import java.io.*;


public class GoodMatch {

    public ArrayList<Integer> charOccurrenceInString(String sentence) {
        ArrayList<Integer> Occurrences = new ArrayList<Integer>();
        String newSentence = (sentence.replaceAll(" ", "")).toLowerCase();

        // Creating a LinkedHashMap containing char
        // as a key and occurrences as a value
        Map<Character, Integer> charCountMap = new LinkedHashMap<>(); // Here we use a LinkedHashMap to maintain the
                                                                      // insertion order as HashMap doesn't maintain the
                                                                      // order.

        // Converting given string to char array

        char[] strArray = newSentence.toCharArray();

        // checking each char of strArray
        for (char c : strArray) {
            if (charCountMap.containsKey(c)) {

                // If char is present in charCountMap,
                // incrementing it's count by 1
                charCountMap.put(c, charCountMap.get(c) + 1);
            } else {

                // If char is not present in charCountMap,
                // putting this char to charCountMap with 1 as it's value
                charCountMap.put(c, 1);
            }
        }

        // Adding the charCountMap value onto the Occurrences arraylist.
        for (Map.Entry entry : charCountMap.entrySet()) {
            Occurrences.add((int) (entry.getValue()));
        }
        return Occurrences;
    }

    static void percentage(ArrayList<Integer> Occurrences) {
        // ArrayList<Integer> perc = new ArrayList<Integer>();
        String ListOccurrences = Occurrences.toString().replaceAll("\\D", ""); // Covert ArrayList into a single list of
                                                                               // numbers
        long ListOccurrencesLong = Long.parseLong(ListOccurrences);
        int SIZE = (int) (Math.log10(ListOccurrencesLong) + 1);
        long percentages[] = new long[SIZE];
        long TotalDigits = (long) (Math.log10(ListOccurrencesLong));
        long FirstNumber = 0;
        long LastNumber = 0;
        long sum = 0;

        while (SIZE > 2) {
            FirstNumber = (ListOccurrencesLong / (long) (Math.pow(10, TotalDigits)));
            LastNumber = (ListOccurrencesLong % 10);
            // RemoveDigits(ListOccurrencesLong);

            // for(int i = 0; i < SIZE; i++){
            // sum = FirstNumber + LastNumber;
            // // percentages[i] = sum;
            // // RemoveDigits(ListOccurrencesLong);

            // }

        }

    }

    public static void RemoveDigits(long ListOccurrencesLong) {

        String s = String.valueOf(ListOccurrencesLong); // Convert Long to String
        StringBuilder sb = new StringBuilder(s); // Create a string Builder
        sb.deleteCharAt(0); // Delete first Value
        sb.deleteCharAt(s.length() - 1); // Delete last value
        String singleString = sb.toString(); // convert string builder to a singel string
        long NewListOccurrencesLong = Long.parseLong(singleString);

        System.out.println(NewListOccurrencesLong);

    }

    public static void main(String[] args) {

        String line = "";
        String splitBy = ",";
        List<String> MalePlayers = new ArrayList<String>();
        List<String> FemalePlayers = new ArrayList<String>();
        try {
            // parsing a CSV file into BufferedReader class constructor and separating players with gender indicator
            BufferedReader br = new BufferedReader(new FileReader("GoodMatchInput.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] players = line.split(splitBy); // use comma as separator
                
                String names = players[0];
                String gender = players[1];
                
                if (gender.equals("f")){
                    FemalePlayers.add(names);
                }
                else{
                    MalePlayers.add(names);

                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
   

    // Pattern p = Pattern.compile("^[a-zA-Z]*$"); // Compile the regex as we'll use it multiple times.
    // String name1 = scan.next();
    // String name2 = scan.next();

    // Making sure that the input is valid.
    // while(p.matcher(name1).find()==false||p.matcher(name2).find()==false)
    // {
    //     System.out.println("Only enter alphabetic characters, please try again.\n");
    //     name1 = scan.next();
    //     name2 = scan.next();
    // }

    String sentence = "";
    GoodMatch obj = new GoodMatch();


    for(int i = 0; i < MalePlayers.size(); i++){
        for(int j = 0; j < FemalePlayers.size(); j++){
            sentence = MalePlayers.get(i) + " matches " + FemalePlayers.get(j);
            System.out.println(obj.charOccurrenceInString(sentence));

        }
    }

}
}
