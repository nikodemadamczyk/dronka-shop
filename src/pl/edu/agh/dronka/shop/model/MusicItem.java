package pl.edu.agh.dronka.shop.model;

public class MusicItem extends Item {
    private MusicGenre genre;
    private boolean includesVideo;

    public MusicItem(String name, Category category, int price, int quantity, MusicGenre genre, boolean includesVideo) {
        super(name, category, price, quantity);
        this.genre = genre;
        this.includesVideo = includesVideo;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public boolean isIncludesVideo() {
        return includesVideo;
    }

    public void setIncludesVideo(boolean includesVideo) {
        this.includesVideo = includesVideo;
    }
}