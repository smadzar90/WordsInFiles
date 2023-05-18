import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;

public class WordsInFiles
{
    private HashMap<String, ArrayList<String>> wordsMap;
    private ArrayList<String> list;
    private ArrayList<String> list2;
    
    public WordsInFiles() {
        wordsMap = new HashMap<String, ArrayList<String>>();
    }
    
    public void addWordsFromFile(File f) {
        
        String fileName = f.getName();
        FileResource fr = new FileResource(f);
        
        for(String word : fr.words()) {
            
            if(!wordsMap.containsKey(word)) {
                list = new ArrayList<String>();
                list.add(fileName);
                wordsMap.put(word, list);
            }
            else {
                boolean contains = false;
                list = wordsMap.get(word);
                
                for(int i = 0; i < list.size(); ++i) {
                    if(list.get(i).equals(fileName)) {
                        contains = true;
                    }
                }
                
                if(!contains) {
                    list.add(fileName);
                    wordsMap.put(word, list);
                }
                    
            }
            
        }
        
    }
    
    public void buildWordFileMap() {
        wordsMap.clear();
        
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
        
    }
    
    public int maxNumber() {
        int numOfFiles = 0;
        
        for(String word : wordsMap.keySet()) {
            list = wordsMap.get(word);
            if(list.size() > numOfFiles) {
                numOfFiles = list.size();
            }
        }
        return numOfFiles;
    }
    
    public ArrayList wordsInNumFiles(int number) {
        list = new ArrayList<String>();
        
        for(String word : wordsMap.keySet()) {
            if(wordsMap.get(word).size() == number) {
                list.add(word);
            }
        }
        return list;
    }
    
    public void printFilesIn(String word) {
        list = wordsMap.get(word);
        for(int i = 0; i < list.size(); ++i) {
            System.out.println(list.get(i));
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int num = maxNumber();
        System.out.println("-->WORDS IN FILES<--\n");
        System.out.println("The greatest number of files a word appears in is: "
        + num + "\n");
        System.out.println("Word(s) are: ");
        list = wordsInNumFiles(num);
        for(int i = 0; i < list.size(); ++i) {
            System.out.println("\"" + list.get(i) + "\"");
        }
        System.out.println();
        for(int i = 0; i < list.size(); ++i) {
            list2 = wordsMap.get(list.get(i));
            System.out.print("\"" + list.get(i) + "\" appears in the files: ");
            for(int a = 0; a < list2.size(); ++a) {
                if(a != list2.size() - 1) {
                  System.out.print(list2.get(a) + ", ");
                }
                else {
                  System.out.print(list2.get(a) + ".");
                }
            }
            System.out.println();
        }
        
        System.out.println("\nNumber of words that occur in " + num + " file(s) is: " +
        list.size());
 
        
    }
    
}
