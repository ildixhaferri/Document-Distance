
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DocumentDistance 
{
    public DocumentDistance()
    {  
    }
  
    // Method to calculate the vector angle
    public Double calculate_Angle(Map<String, Integer> map1, Map<String, Integer> map2) 
    {
        Double numerator = Inner_Product(map1, map2);
        Double denominator = Math.sqrt(Inner_Product(map1, map1) * Inner_Product(map2, map2));
        //System.out.println(numerator);
        //System.out.println(denominator);
        return Math.acos(numerator/denominator);
    }

    // Method to clculate the Inner Product
    public Double Inner_Product(Map<String, Integer> map1, Map<String, Integer> map2) 
    {
        Double sum = 0.0;

        for (String key : map1.keySet()) 
        {
            if (map2.containsKey(key)) 
            {
                sum += map1.get(key) * map2.get(key);
            }
        }

        return sum;
    }

    // Method to calculate Total Number of Words and Number of Distinct Words, for each file
    public Map<String, Integer> calculateWordFreqForFile(Path path) 
    {
        String fileContent = read_the_File(path);
        List<String> words = getWords_FromString(fileContent);
        Map<String, Integer> freqMapping = word_Frequency(words);

        System.out.print("File : " + path.toString() + " contains : ");
        System.out.print(words.size() + " words, ");
        System.out.println(freqMapping.size() + " Distinct words");

        return freqMapping;
    }

    public Map<String, Integer> calculateWordFreqForString(String string){
    
        List<String> words = getWords_FromString(string);
        Map<String, Integer> freqMapping = word_Frequency(words);
        
        return freqMapping;
        
    }
    // Method to Read a text file
    public String read_the_File(Path path) 
    {
        // Charset --A coded character set is a mapping between a set of abstract characters and a set of integers.
        //US-ASCII, ISO 8859-1, JIS X 0201, and Unicode are examples of coded character sets.
        
        /*
        A charset is a named mapping between Unicode characters and byte sequences. 
        Every Charset can decode, converting a byte sequence into a sequence of characters,
        and some can also encode, converting a sequence of characters into a byte sequence. 
         
        */
        
        try {
            Charset encoding = Charset.defaultCharset();
            byte[] encoded = Files.readAllBytes(path);
            return encoding.decode(ByteBuffer.wrap(encoded)).toString();
        } catch (IOException e) 
        {
            System.err.println("Error opening or reading input file:" + path.toString());
            e.printStackTrace();
        }

        return null;
    }

    // getWords_FromString- Method to Split the text lines into words 
    public List<String> getWords_FromString(String line) 
    {
        List<String> words = new ArrayList();
        
        for (String word : line.split("[^a-zA-Z0-9]+")) 
        {
            
            words.add(word.toLowerCase());
           // System.out.println(word.toLowerCase());
        }
        
        return words;
    }

    // word_Frequency- method to count frequency of each word
    public Map<String, Integer> word_Frequency(List<String> words) 
    {
        Map<String, Integer> freq = new HashMap();

        for (String keyword : words) 
        {
            if (freq.containsKey(keyword)) 
                freq.put(keyword, freq.get(keyword) + 1);
             else 
                freq.put(keyword, 1);
            
        }

        return freq;
    }
    
 /*
 Map is an interface that HashMap implements. 
 The difference is that in the second implementation your reference to the 
 HashMap will only allow the use of functions defined in the Map interface, 
 while the first will allow the use of any public functions in HashMap (which includes the Map interface).
*/
}