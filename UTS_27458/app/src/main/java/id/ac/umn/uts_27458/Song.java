package id.ac.umn.uts_27458;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    private final String path;
    private final String name;
    private final String artist;

    public Song(String path, String name, String artist) {
        this.path = path;
        this.name = name;
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }


    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    protected Song(Parcel in) {
        path = in.readString();
        name = in.readString();
        artist = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(name);
        dest.writeString(artist);
    }


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}
