package dataStructures.basicChecks;

import dataStructures.ActorMovieRelationModel;
import dataStructures.Graph;
import dataStructures.exceptions.DSStandardException;

import java.util.Arrays;

public class FindRelationBetweenActors {

    public static void main(String... args) throws DSStandardException {
        Graph<ActorMovieRelationModel.MovieActor> inputGraph = new Graph<>(true);
        ActorMovieRelationModel actorMovieRelationModel = new ActorMovieRelationModel();

        actorMovieRelationModel.addEntries(inputGraph, "Padayappa", Arrays.asList("Rajini", "Ramyakrishnan", "Soundharya", "Senthil", "RadhaRavi", "RameshKhanna"));
        actorMovieRelationModel.addEntries(inputGraph, "Panchathanthiram", Arrays.asList("Kamal", "Ramyakrishnan", "Jayaram", "YugiSedhu", "RameshAravind", "Nagesh"));
        actorMovieRelationModel.addEntries(inputGraph, "Komban", Arrays.asList("Karthik", "LakshmiMenon", "KovaiSarala", "Karunas", "Rajkiran"));
        actorMovieRelationModel.addEntries(inputGraph, "Kaala", Arrays.asList("Rajini", "Samuthirakkani", "HeemaKureshi", "Sampath"));
        actorMovieRelationModel.addEntries(inputGraph, "Madras", Arrays.asList("Karthik", "CatherineTeresa", "Rithvika", "Kalaiarasan"));
        actorMovieRelationModel.addEntries(inputGraph, "Mankatha", Arrays.asList("Ajith", "LakshmiRoy", "Vaibhav", "Anjali", "Arjun", "Andreah"));
        actorMovieRelationModel.addEntries(inputGraph, "Saroja", Arrays.asList("Premji", "MirchiSiva", "Vaibhav", "Jayaram"));

        //System.out.println(actorMovieRelationModel.findRelationBetweenTwoActors(inputGraph, "Rajini", "Kamal"));
        //System.out.println(actorMovieRelationModel.findRelationBetweenTwoActors(inputGraph, "Rajini", "Vaibhav"));
        //System.out.println(actorMovieRelationModel.findRelationBetweenTwoActors(inputGraph, "Rajini", "Anjali"));
        System.out.println(actorMovieRelationModel.findRelationBetweenTwoActors(inputGraph, "Rajini", "Rajkiran"));
    }
}
