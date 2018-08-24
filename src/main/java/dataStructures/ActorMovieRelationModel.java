package dataStructures;

import dataStructures.exceptions.DSStandardException;

import java.util.LinkedList;
import java.util.List;

public class ActorMovieRelationModel {

    public void addEntries(Graph<MovieActor> inputGraph, String movieName,
                           List<String> actors) throws DSStandardException{
        if(inputGraph != null && movieName != null && actors != null) {
            MovieActor movie = new MovieActor(movieName, true);
            inputGraph.add(movie);
            for(String actor: actors) {
                MovieActor actor1 = new MovieActor(actor, false);
                inputGraph.add(actor1);
                inputGraph.addEdge(movie, actor1);
            }
        }
        else {
            throw new DSStandardException("No Null Arguments");
        }
    }

    public String findRelationBetweenTwoActors(Graph<MovieActor> inputGraph,
                                             String actor1, String actor2) throws DSStandardException{
        Graph.Vertice actor1Vertice = inputGraph.find(new MovieActor(actor1, false));
        Graph.Vertice actor2Vertice = inputGraph.find(new MovieActor(actor2, false));
        int friendshipLevel = 0;

        LinkedList<Graph.Edge> movieNames = new LinkedList<>();
        LinkedList<Graph.Vertice> actorNames = new LinkedList<>();
        movieNames.addAll(actor1Vertice.getEdges());

        while(movieNames.size() > 0 || actorNames.size() > 0) {
            if(movieNames.size() == 0) {
                while(actorNames.size() > 0) {
                    Graph.Vertice nextActor = actorNames.remove();
                    if(nextActor.isVisited()) {
                        continue;
                    }
                    movieNames.addAll(nextActor.getEdges());
                    nextActor.setVisited(true);
                }
                friendshipLevel++;
            }
            else {
                Graph.Vertice nextMovie = movieNames.remove().getToNode();
                if(nextMovie.isVisited()) {
                    continue;
                }
                else {
                    nextMovie.setVisited(true);
                    for(Object nextActor: nextMovie.getEdges()) {
                    if(actor2Vertice.equals(((Graph.Edge)nextActor).getToNode())) {
                        return actor1 + " and " + actor2 + " has " + friendshipLevel + " friendship";
                    }
                    actorNames.add(((Graph.Edge)nextActor).getToNode());
                    }
                }
            }
        }
        return actor1 + " has No Relation with " + actor2;
    }

    public class MovieActor {
        private final String name;
        private final boolean isMovieName;

        public MovieActor(String name, boolean isMovieName) throws DSStandardException {
            if(name == null) {
                throw new DSStandardException("No Null");
            }
            this.name = name;
            this.isMovieName = isMovieName;
        }

        public String getName() {
            return name;
        }

        public boolean isMovieName() {
            return isMovieName;
        }

        public boolean equals(Object o) {
            if(o instanceof MovieActor) {
                return this.getName().equals(((MovieActor)o).getName()) &&
                        this.isMovieName() == ((MovieActor)o).isMovieName();
            }
            return false;
        }

        public int hashCode() {
            return this.getName().hashCode() * 1000 + (this.isMovieName() ? 1 : 2);
        }

        public String toString() {
            return this.getName() + (this.isMovieName() ? " Movie" : " Actor");
        }
    }
}
