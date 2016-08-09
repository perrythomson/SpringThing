package springBootApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;  //where to find the addition of JSP in application

import java.util.ArrayList;

@Controller
public class JspController {

    @RequestMapping(value="/")
    public String jspIndex() {
        return "index";
    }

    @RequestMapping(value="/jspTest")
    public String jspTest() {
        return "test";
    }

    @RequestMapping(value="/stringThing")
    public String stringThing() {
        return "stringThing";
    }

    @RequestMapping(value="/generatePhrase")
    public String generatePhrase(String sentence, ModelMap map) {
        map.addAttribute("sentence",sentence);

        //Count words in the phrase.
        //this is one of the key words \\s+
        String[] words = sentence.split("\\s+");
        int wordCount = words.length;
        map.addAttribute("wordCount",wordCount);

        //Count the characters in the sentence.
        char[] characters = sentence.toCharArray();
        int characterCount = characters.length;
        map.addAttribute("characterCount", characterCount);

        //Reverse the character string ***week 2****
        StringBuilder reverseString = new StringBuilder();
        reverseString.append(sentence);
        reverseString.reverse();
        map.addAttribute("reverseString",reverseString);

        //Translate into piglatin...other languages
        String pigLatin = pigLatinTranslator(words);
        map.addAttribute("pigLatin",pigLatin);

        //Create google links from words/phrases/characters
        ArrayList<String> googleLinks = new ArrayList<>();
        for(String word : words) {
            googleLinks.add("https://www.google.com/webhp?#safe=off&hl=en&q="+word);
        }
        map.addAttribute("googleLinks",googleLinks);
        return "stringThing";
    }

    private String pigLatinTranslator(String[] words) {
        String vowels = "aeiouAEIOU"; //identify breaks
        String pigLatin = ""; //empty field
        for(String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (vowels.contains(""+word.charAt(i))) { //breaking down the word
                    String prefix = word.substring(0, i);
                    String suffix = word.substring(i);
                    word = suffix + prefix + "ay";
                    break;//stop piglatin
                }
            }
            pigLatin += word+ " ";
        }
        return pigLatin;
    }
}
