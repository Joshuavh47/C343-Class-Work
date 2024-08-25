import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {
    @Test
    public void searchTest() throws IOException{
        long timeStart1=System.currentTimeMillis();
        SearchEngine s5=new SearchEngine(5);
        s5.buildList();
        List<String> output5=s5.search("the");
        String str5="";
        for(String x:output5){
            str5+=x+"\n";
        }
        str5=str5.substring(0,str5.length()-1);
        long timeEnd1=System.currentTimeMillis();
        long difference1=timeEnd1-timeStart1;
        assertEquals("https://en.wikipedia.org/wiki/Computer_Science\nhttps://en.wikipedia.org/wiki/Statistics\nhttps://en.wikipedia.org/wiki/Machine_learning\nhttps://en.wikipedia.org/wiki/Computer_vision\nhttps://en.wikipedia.org/wiki/Functional_programming\nhttps://en.wikipedia.org/wiki/K-pop\nhttps://en.wikipedia.org/wiki/Blackpink\nhttps://en.wikipedia.org/wiki/BTS\nhttps://en.wikipedia.org/wiki/Democratic_Party_(United_States)\nhttps://en.wikipedia.org/wiki/Republican_Party_(United_States)\nhttps://en.wikipedia.org/wiki/Taiwan\nhttps://en.wikipedia.org/wiki/China\nhttps://en.wikipedia.org/wiki/India\nhttps://en.wikipedia.org/wiki/Canada\nhttps://en.wikipedia.org/wiki/American_Civil_War\nhttps://en.wikipedia.org/wiki/Chinese_Civil_War\nhttps://en.wikipedia.org/wiki/French_Revolution\nhttps://en.wikipedia.org/wiki/Google\nhttps://en.wikipedia.org/wiki/Presidential_elections_in_Singapore\nhttps://en.wikipedia.org/wiki/Black_hole\nhttps://en.wikipedia.org/wiki/Red_Velvet_(group)\nhttps://en.wikipedia.org/wiki/Kendrick_Lamar\nhttps://en.wikipedia.org/wiki/Daniel_P._Friedman\nhttps://en.wikipedia.org/wiki/Linear_algebra\nhttps://en.wikipedia.org/wiki/Graph_theory\nhttps://en.wikipedia.org/wiki/Valorant\nhttps://en.wikipedia.org/wiki/League_of_Legends\nhttps://en.wikipedia.org/wiki/FIFA_World_Cup\nhttps://en.wikipedia.org/wiki/Pickleball\nhttps://en.wikipedia.org/wiki/National_Hockey_League\nhttps://en.wikipedia.org/wiki/OpenAI\nhttps://en.wikipedia.org/wiki/Competitive_eating\nhttps://en.wikipedia.org/wiki/COVID-19\nhttps://en.wikipedia.org/wiki/Naomi_Osaka\nhttps://en.wikipedia.org/wiki/Serena_Williams\nhttps://en.wikipedia.org/wiki/Kobe_Bryant\nhttps://en.wikipedia.org/wiki/Ukraine\nhttps://en.wikipedia.org/wiki/Animal_Crossing\nhttps://en.wikipedia.org/wiki/Minecraft\nhttps://en.wikipedia.org/wiki/Cloud_computing\nhttps://en.wikipedia.org/wiki/Agile_software_development\nhttps://en.wikipedia.org/wiki/Jeremy_Bentham\nhttps://en.wikipedia.org/wiki/Feminism\nhttps://en.wikipedia.org/wiki/McDonald%27s\nhttps://en.wikipedia.org/wiki/Riot_Games\nhttps://en.wikipedia.org/wiki/GitHub\nhttps://en.wikipedia.org/wiki/Quantum_mechanics\nhttps://en.wikipedia.org/wiki/Quantitative_fund\nhttps://en.wikipedia.org/wiki/Quantum_computing\nhttps://en.wikipedia.org/wiki/Pok%C3%A9mon\nhttps://en.wikipedia.org/wiki/Norway_women%27s_national_football_team\nhttps://en.wikipedia.org/wiki/Cat\nhttps://en.wikipedia.org/wiki/Dog\nhttps://en.wikipedia.org/wiki/Human_rights\nhttps://en.wikipedia.org/wiki/Civil_rights_movement\nhttps://en.wikipedia.org/wiki/Kamala_Harris\nhttps://en.wikipedia.org/wiki/Mike_Pence\nhttps://en.wikipedia.org/wiki/January_6_United_States_Capitol_attack\nhttps://en.wikipedia.org/wiki/Assassination_attempts_on_Fidel_Castro\nhttps://en.wikipedia.org/wiki/Nikola_Tesla\nhttps://en.wikipedia.org/wiki/Naruto\nhttps://en.wikipedia.org/wiki/Jujutsu_Kaisen\nhttps://en.wikipedia.org/wiki/Bleach_(manga)\nhttps://en.wikipedia.org/wiki/Spy_%C3%97_Family\nhttps://en.wikipedia.org/wiki/SZA\nhttps://en.wikipedia.org/wiki/Beyonc%C3%A9\nhttps://en.wikipedia.org/wiki/Indiana_University_Bloomington",str5);
        assertEquals(true,difference1<60000);


        long timeStart2=System.currentTimeMillis();
        SearchEngine s6=new SearchEngine(6);
        s6.buildList();
        List<String> output6=s6.search("the");
        String str6="";
        for(String x:output6){
            str6+=x+"\n";
        }
        str6=str6.substring(0,str6.length()-1);

        long timeEnd2=System.currentTimeMillis();
        long difference2=timeEnd2-timeStart2;
        assertEquals("https://en.wikipedia.org/wiki/Computer_Science\nhttps://en.wikipedia.org/wiki/Statistics\nhttps://en.wikipedia.org/wiki/Machine_learning\nhttps://en.wikipedia.org/wiki/Computer_vision\nhttps://en.wikipedia.org/wiki/Functional_programming\nhttps://en.wikipedia.org/wiki/K-pop\nhttps://en.wikipedia.org/wiki/Blackpink\nhttps://en.wikipedia.org/wiki/BTS\nhttps://en.wikipedia.org/wiki/Democratic_Party_(United_States)\nhttps://en.wikipedia.org/wiki/Republican_Party_(United_States)\nhttps://en.wikipedia.org/wiki/Taiwan\nhttps://en.wikipedia.org/wiki/China\nhttps://en.wikipedia.org/wiki/India\nhttps://en.wikipedia.org/wiki/Canada\nhttps://en.wikipedia.org/wiki/American_Civil_War\nhttps://en.wikipedia.org/wiki/Chinese_Civil_War\nhttps://en.wikipedia.org/wiki/French_Revolution\nhttps://en.wikipedia.org/wiki/Google\nhttps://en.wikipedia.org/wiki/Presidential_elections_in_Singapore\nhttps://en.wikipedia.org/wiki/Black_hole\nhttps://en.wikipedia.org/wiki/Red_Velvet_(group)\nhttps://en.wikipedia.org/wiki/Kendrick_Lamar\nhttps://en.wikipedia.org/wiki/Daniel_P._Friedman\nhttps://en.wikipedia.org/wiki/Linear_algebra\nhttps://en.wikipedia.org/wiki/Graph_theory\nhttps://en.wikipedia.org/wiki/Valorant\nhttps://en.wikipedia.org/wiki/League_of_Legends\nhttps://en.wikipedia.org/wiki/FIFA_World_Cup\nhttps://en.wikipedia.org/wiki/Pickleball\nhttps://en.wikipedia.org/wiki/National_Hockey_League\nhttps://en.wikipedia.org/wiki/OpenAI\nhttps://en.wikipedia.org/wiki/Competitive_eating\nhttps://en.wikipedia.org/wiki/COVID-19\nhttps://en.wikipedia.org/wiki/Naomi_Osaka\nhttps://en.wikipedia.org/wiki/Serena_Williams\nhttps://en.wikipedia.org/wiki/Kobe_Bryant\nhttps://en.wikipedia.org/wiki/Ukraine\nhttps://en.wikipedia.org/wiki/Animal_Crossing\nhttps://en.wikipedia.org/wiki/Minecraft\nhttps://en.wikipedia.org/wiki/Cloud_computing\nhttps://en.wikipedia.org/wiki/Agile_software_development\nhttps://en.wikipedia.org/wiki/Jeremy_Bentham\nhttps://en.wikipedia.org/wiki/Feminism\nhttps://en.wikipedia.org/wiki/McDonald%27s\nhttps://en.wikipedia.org/wiki/Riot_Games\nhttps://en.wikipedia.org/wiki/GitHub\nhttps://en.wikipedia.org/wiki/Quantum_mechanics\nhttps://en.wikipedia.org/wiki/Quantitative_fund\nhttps://en.wikipedia.org/wiki/Quantum_computing\nhttps://en.wikipedia.org/wiki/Pok%C3%A9mon\nhttps://en.wikipedia.org/wiki/Norway_women%27s_national_football_team\nhttps://en.wikipedia.org/wiki/Cat\nhttps://en.wikipedia.org/wiki/Dog\nhttps://en.wikipedia.org/wiki/Human_rights\nhttps://en.wikipedia.org/wiki/Civil_rights_movement\nhttps://en.wikipedia.org/wiki/Kamala_Harris\nhttps://en.wikipedia.org/wiki/Mike_Pence\nhttps://en.wikipedia.org/wiki/January_6_United_States_Capitol_attack\nhttps://en.wikipedia.org/wiki/Assassination_attempts_on_Fidel_Castro\nhttps://en.wikipedia.org/wiki/Nikola_Tesla\nhttps://en.wikipedia.org/wiki/Naruto\nhttps://en.wikipedia.org/wiki/Jujutsu_Kaisen\nhttps://en.wikipedia.org/wiki/Bleach_(manga)\nhttps://en.wikipedia.org/wiki/Spy_%C3%97_Family\nhttps://en.wikipedia.org/wiki/SZA\nhttps://en.wikipedia.org/wiki/Beyonc%C3%A9\nhttps://en.wikipedia.org/wiki/Indiana_University_Bloomington",str6);
        assertEquals(true,difference2<60000);


        long timeStart3=System.currentTimeMillis();
        SearchEngine s51=new SearchEngine(5);
        s51.buildList();
        List<String> output51=s51.search("science");
        String str51="";
        for(String x:output51){
            str51+=x+"\n";
        }
        str51=str51.substring(0,str51.length()-1);
        long timeEnd3=System.currentTimeMillis();
        long difference3=timeEnd3-timeStart3;
        assertEquals("https://en.wikipedia.org/wiki/Computer_Science\nhttps://en.wikipedia.org/wiki/Statistics\nhttps://en.wikipedia.org/wiki/Machine_learning\nhttps://en.wikipedia.org/wiki/Computer_vision\nhttps://en.wikipedia.org/wiki/Functional_programming\nhttps://en.wikipedia.org/wiki/K-pop\nhttps://en.wikipedia.org/wiki/Democratic_Party_(United_States)\nhttps://en.wikipedia.org/wiki/Republican_Party_(United_States)\nhttps://en.wikipedia.org/wiki/Taiwan\nhttps://en.wikipedia.org/wiki/China\nhttps://en.wikipedia.org/wiki/India\nhttps://en.wikipedia.org/wiki/Canada\nhttps://en.wikipedia.org/wiki/American_Civil_War\nhttps://en.wikipedia.org/wiki/Chinese_Civil_War\nhttps://en.wikipedia.org/wiki/French_Revolution\nhttps://en.wikipedia.org/wiki/Google\nhttps://en.wikipedia.org/wiki/Black_hole\nhttps://en.wikipedia.org/wiki/Daniel_P._Friedman\nhttps://en.wikipedia.org/wiki/Linear_algebra\nhttps://en.wikipedia.org/wiki/Graph_theory\nhttps://en.wikipedia.org/wiki/League_of_Legends\nhttps://en.wikipedia.org/wiki/OpenAI\nhttps://en.wikipedia.org/wiki/COVID-19\nhttps://en.wikipedia.org/wiki/Serena_Williams\nhttps://en.wikipedia.org/wiki/Ukraine\nhttps://en.wikipedia.org/wiki/Cloud_computing\nhttps://en.wikipedia.org/wiki/Agile_software_development\nhttps://en.wikipedia.org/wiki/Jeremy_Bentham\nhttps://en.wikipedia.org/wiki/Feminism\nhttps://en.wikipedia.org/wiki/McDonald%27s\nhttps://en.wikipedia.org/wiki/Riot_Games\nhttps://en.wikipedia.org/wiki/Quantum_mechanics\nhttps://en.wikipedia.org/wiki/Quantum_computing\nhttps://en.wikipedia.org/wiki/Cat\nhttps://en.wikipedia.org/wiki/Dog\nhttps://en.wikipedia.org/wiki/Human_rights\nhttps://en.wikipedia.org/wiki/Civil_rights_movement\nhttps://en.wikipedia.org/wiki/Kamala_Harris\nhttps://en.wikipedia.org/wiki/Mike_Pence\nhttps://en.wikipedia.org/wiki/January_6_United_States_Capitol_attack\nhttps://en.wikipedia.org/wiki/Nikola_Tesla\nhttps://en.wikipedia.org/wiki/Beyonc%C3%A9\nhttps://en.wikipedia.org/wiki/Indiana_University_Bloomington",str51);
        assertEquals(true,difference3<60000);

        long timeStart4=System.currentTimeMillis();
        SearchEngine s61=new SearchEngine(6);
        s61.buildList();
        List<String> output61=s61.search("science");
        String str61="";
        for(String x:output61){
            str61+=x+"\n";
        }
        str61=str61.substring(0,str61.length()-1);
        long timeEnd4=System.currentTimeMillis();
        long difference4=timeEnd4-timeStart4;
        assertEquals("https://en.wikipedia.org/wiki/Computer_Science\nhttps://en.wikipedia.org/wiki/Statistics\nhttps://en.wikipedia.org/wiki/Machine_learning\nhttps://en.wikipedia.org/wiki/Computer_vision\nhttps://en.wikipedia.org/wiki/Functional_programming\nhttps://en.wikipedia.org/wiki/K-pop\nhttps://en.wikipedia.org/wiki/Democratic_Party_(United_States)\nhttps://en.wikipedia.org/wiki/Republican_Party_(United_States)\nhttps://en.wikipedia.org/wiki/Taiwan\nhttps://en.wikipedia.org/wiki/China\nhttps://en.wikipedia.org/wiki/India\nhttps://en.wikipedia.org/wiki/Canada\nhttps://en.wikipedia.org/wiki/American_Civil_War\nhttps://en.wikipedia.org/wiki/Chinese_Civil_War\nhttps://en.wikipedia.org/wiki/French_Revolution\nhttps://en.wikipedia.org/wiki/Google\nhttps://en.wikipedia.org/wiki/Black_hole\nhttps://en.wikipedia.org/wiki/Daniel_P._Friedman\nhttps://en.wikipedia.org/wiki/Linear_algebra\nhttps://en.wikipedia.org/wiki/Graph_theory\nhttps://en.wikipedia.org/wiki/League_of_Legends\nhttps://en.wikipedia.org/wiki/OpenAI\nhttps://en.wikipedia.org/wiki/COVID-19\nhttps://en.wikipedia.org/wiki/Serena_Williams\nhttps://en.wikipedia.org/wiki/Ukraine\nhttps://en.wikipedia.org/wiki/Cloud_computing\nhttps://en.wikipedia.org/wiki/Agile_software_development\nhttps://en.wikipedia.org/wiki/Jeremy_Bentham\nhttps://en.wikipedia.org/wiki/Feminism\nhttps://en.wikipedia.org/wiki/McDonald%27s\nhttps://en.wikipedia.org/wiki/Riot_Games\nhttps://en.wikipedia.org/wiki/Quantum_mechanics\nhttps://en.wikipedia.org/wiki/Quantum_computing\nhttps://en.wikipedia.org/wiki/Cat\nhttps://en.wikipedia.org/wiki/Dog\nhttps://en.wikipedia.org/wiki/Human_rights\nhttps://en.wikipedia.org/wiki/Civil_rights_movement\nhttps://en.wikipedia.org/wiki/Kamala_Harris\nhttps://en.wikipedia.org/wiki/Mike_Pence\nhttps://en.wikipedia.org/wiki/January_6_United_States_Capitol_attack\nhttps://en.wikipedia.org/wiki/Nikola_Tesla\nhttps://en.wikipedia.org/wiki/Beyonc%C3%A9\nhttps://en.wikipedia.org/wiki/Indiana_University_Bloomington",str61);
        assertEquals(true,difference4<60000);

    }

}