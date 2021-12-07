import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.InvalidPathException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors ;

public class GoodMatchEM {

    public String charOccurrenceInString(String sentence){
        ArrayList<Integer> Occurrences = new ArrayList<Integer>();
        String newSentence = (sentence.replaceAll(" ", "")).toLowerCase();
        
        // Creating a LinkedHashMap containing char
		// as a key and occurrences as a value
		Map<Character, Integer> charCountMap = new LinkedHashMap<>(); //Here we use a LinkedHashMap to maintain the insertion order as HashMap doesn't maintain the order.

		// Converting given string to char array

		char[] strArray = newSentence.toCharArray();

		// checking each char of strArray
		for (char c : strArray) {
			if (charCountMap.containsKey(c)) {

				// If char is present in charCountMap,
				// incrementing it's count by 1
				charCountMap.put(c, charCountMap.get(c) + 1);
			}
			else {

				// If char is not present in charCountMap,
				// putting this char to charCountMap with 1 as it's value
				charCountMap.put(c, 1);
			}
		}

		// Adding the charCountMap value onto the Occurrences arraylist.
		for (Map.Entry entry : charCountMap.entrySet()) {
			Occurrences.add((int)(entry.getValue()));
		}
        return Occurrences.stream().map(Object::toString).collect(Collectors.joining(""));
    }

    public static String reduceString(String long_num){
        ArrayList<Long> arrL = new ArrayList<Long>();
        long vl = 0;
        while(long_num.length() > 1){
            long n = Long.parseLong(long_num);
            long lastn = n % 10;
            // Remove last digit from number
            // till only one digit is left
            long firstn = n;
            while (firstn >= 10){
                firstn /= 10;
            }
            
            arrL.add(firstn + lastn);
            if(n !=lastn){
                String newL = long_num.substring(1, long_num.length() - 1);
                long_num = newL;
            }
            vl = lastn;    
        }
        
        if(long_num.length() == 1 ){
            arrL.add(Long.parseLong(long_num));
        }
        long_num = arrL.stream().map(Object::toString).collect(Collectors.joining(""));
    
		return long_num;
	}
    public static void printResult(String result, String sentence){
        for (int i = 0; i < result.length() - 1; i++){
            result = GoodMatchEM.reduceString(result);
        }
        int benchmark = 80;
        int percentage = Integer.parseInt(GoodMatchEM.reduceString(result));
        if(percentage > benchmark){
            System.out.println(sentence + " " + percentage + "%, good match"); 
        }
        else{
            System.out.println(sentence + " " + percentage + "%"); 
        }

    }

    public static void main(String[] args){
        // Scanner scan = new Scanner(System.in);

        // Pattern p = Pattern.compile("^[a-zA-Z]*$"); // Compile the regex as we'll use it multiple times.
        // String name1 = scan.next();
        // String name2 = scan.next();

        // // Making sure that the input is valid.
        // while(p.matcher(name1).find() == false || p.matcher(name2).find() == false){
        //     System.out.println("Only enter alphabetic characters, please try again.\n");
        //     name1 = scan.next();
        //     name2 = scan.next();
        // }
        // String sentence = name1 + " matches " + name2;
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
            
        } catch (InvalidPathException e) {
            e.printStackTrace();
        }
   



    // String sentence = "";
    GoodMatchEM obj = new GoodMatchEM();


    for(int i = 0; i < MalePlayers.size(); i++){
        for(int j = 0; j < FemalePlayers.size(); j++){

            String sentence = MalePlayers.get(i) + " matches " + FemalePlayers.get(j);
            System.out.println(sentence);
            String nstring = GoodMatchEM.reduceString(obj.charOccurrenceInString(sentence));
            obj.printResult(nstring, sentence);
            // System.out.println(obj.reduceString(sentence));

        }
    }
        
        // obj = new GoodMatch();
        
        
        // Print the result
        
        
    }
}    
