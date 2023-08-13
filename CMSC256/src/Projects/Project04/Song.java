package Projects.Project04;
/*
 * Project 04 : Song object list
 * This program is used to manage a music collection, this is an object
 * class used to represent a song but using its title, artist, album, and playcount
 * Lorelai Davis
 * CMSC 256 Section C01
 * 15 June 2023
 */
import java.util.Objects;

public class Song implements Comparable<Song>{
    private String title,artist,album;
    private int playcount;

    public Song(){ //default constructor, sets all variables to empty values
        title = "";
        artist = "";
        album = "";
        this.playcount = 0;
    }
    public Song(String title,String artist,String album,int playcount) throws IllegalArgumentException{
        this.title = title;
        this.artist = artist;
        this.album = album;
        if(playcount >= 0){ //if playcount is negative an exception will be thrown
            this.playcount = playcount;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getTitle() {
        return title;
    }public int getPlaycount() {
        return playcount;
    }public String getAlbum() {
        return album;
    }public String getArtist() {
        return artist;
    }
    //setters for variables, checks if values are null or zero and will throw an exception if so
    public void setAlbum(String album) {
        if(album == null){
            throw new IllegalArgumentException();
        }
        this.album = album;
    }public void setArtist(String artist) {
        if(artist == null){
            throw new IllegalArgumentException();
        }
        this.artist = artist;
    }public void setPlaycount(int playCount) {
        if(playCount < 0){
            throw new IllegalArgumentException();
        }
        this.playcount = playCount;
    }public void setTitle(String title) {
        if(title == null){
            throw new IllegalArgumentException();
        }
        this.title = title;
    }

    @Override
    public String toString() {
        return getTitle()+" : "+getArtist()+" : "+getAlbum()+
                " : "+getPlaycount();
    }

    /**
     * compareTo method compares first the artist, then the album, then the title
     * @param o the object to be compared.
     * @return 0, -1, or 1
     */
    @Override
    public int compareTo(Song o) {
        if(this.equals(o)){ //if the two objects are the same thing returns 0
            return 0;
        }
        if(this.getArtist() == o.getArtist()){ //if the artist is the same
            if(this.getAlbum() == o.getAlbum()){ //if the album is the same as well as the artist
                //uses the string compareTo method to get an int value
                int val = this.getTitle().compareTo(o.getTitle());
                if(val < 0){
                    return -1;
                }if(val > 0){
                    return 1;
                }
            }else{ //if the albums are different but the artist is the same
                int val = this.getAlbum().compareTo(o.getAlbum());
                if(val < 0){
                    return -1;
                }else if(val > 0){
                    return 1;
                }
            }
        }else{ //if the artists are different
            int val = this.getArtist().compareTo(o.getArtist());
            if(val < 0){
                return -1;
            }else if(val > 0){
                return 1;
            }
        }
        return 0; //will return 0 for default
    }

    /**
     * extra method to compare playcount values
     * @param o
     * @return returns an int value after comparing playcount values
     */
    public int compareToPlay(Song o){
        return Integer.compare(o.getPlaycount(), this.getPlaycount());
    }

    @Override
    public boolean equals(Object o) { //checks if objects are equal, is paired with the hashcode method
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return playcount == song.playcount && Objects.equals(title, song.title) && Objects.equals(artist, song.artist) && Objects.equals(album, song.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, album, playcount);
    }
}
