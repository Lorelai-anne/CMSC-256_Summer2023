package Projects.Project04;
/*
 * Project 04 : Song object list
 * This program is used to manage a music collection, the class itself
 * is used to manage a list of Song objects
 * Lorelai Davis
 * CMSC 256 Section C01
 * 15 June 2023
 */
import java.io.*;
import java.util.*;

public class MusicManager {
    private List<Song> songList;

    public MusicManager(){ //class constructor
        songList = new LinkedList<>(); //only creates a new List
    }

    /**
     * accepts a song object to add to the songList List
     * @param song Song object to be added
     */
    public void addSong(Song song){
        if(song == null) { //if the song object is null or empty an exception is thrown
            throw new NullPointerException();
        }
        songList.add(song); //adds the object to the list
    }
    public void readPlaylistFile(String fileName) throws FileNotFoundException{
        String title = "",album = "",artist = "";
        int playCount = 0;
        Scanner sc = new Scanner(new File(fileName));
        ArrayList<String> temp = new ArrayList<>();
        while(sc.hasNextLine()){
            String nextLine = sc.nextLine();
            temp.add(nextLine);
        }
        sc.close();
        for(String temps : temp) {
            String[] songs = temps.split(":");
            title = songs[0];
            album = songs[1];
            artist = songs[2];
            try{
                playCount = Integer.parseInt(songs[3]);
            }catch(NumberFormatException ignored){
            }
            Song song = new Song(title,artist,album,playCount);
            songList.add(song);
        }
    }

    /**
     * Accepts a list and an artist name, will return true if the list contains
     * any songs by the given artist
     * @param artist String value of an artist
     * @return boolean values true or false
     */
    public boolean hasSongsBy(String artist){
        if(artist == null){ //throws exception if parameter value is null
            throw new NullPointerException();
        }for (Song song : songList) { //enhanced for loop
            if (song.getArtist().equalsIgnoreCase(artist)) { //if an object that has the artist value is encountered
                return true; //true is returned
            }
        }
        return false; //returns false if the list does not have an object with the artist value
    }

    /**
     * Accepts a list of songs and an artist name and returns a list of songs
     * created by that artist
     * @param artist String value of desired artist
     * @return Linked List of Song objects
     */
    public List<Song> getSongsBy(String artist){
        List<Song> artistList = new LinkedList<>(); //creates a Song object list to return
        if(artist == null){ //throws an exception if the parameter artist value is null
            throw new NullPointerException();
        }for(Song song : songList){ //enhanced for loop
            if(song.getArtist().equalsIgnoreCase(artist)){ //ignores upper and lowercase
                artistList.add(song); //adds the song objects if they old the value of the parameter in the artist space
            }
        }
        artistList.sort(Song :: compareTo); //sorts the List by artist, then album, and then title
        return artistList;
    }

    /**
     * returns an array of five song objects with the highest playcount value
     * @return Array of Song objects
     */
    public Song[] getTop5Songs(){
        songList.sort(Song::compareToPlay); //sorts the list by playList
        Song[] top5 = new Song[5]; //creates an array to return that can only hold five values
        for(int i=0;i<top5.length;i++){
            top5[i] = songList.get(i); //populates array with the first five values of songlist
        }
        return top5;
    }
}
