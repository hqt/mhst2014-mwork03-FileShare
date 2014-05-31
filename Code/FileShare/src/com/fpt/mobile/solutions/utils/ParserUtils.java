package com.fpt.mobile.solutions.utils;

/**
 * Created by ThaoHQSE60963 on 5/30/14.
 */
public class ParserUtils {
    private static String TAG = LogUtils.makeLogTag(ParserUtils.class);

    //region Parse Data from String
    ///////////////////////////////////////////////////////////////
    /////////////// PARSE DATA FROM STRING ////////////////////////

    /*public static List<Song> parseAllSongsFromJSONString(String json) {
        try {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(json).getAsJsonArray();
            return parseSongsFromJsonArray(jsonArray);
        } catch (IllegalStateException e) {
            // The response is not a JSON array. (May be the response is too big or null
            e.printStackTrace();
        }
        return null;
    }

    public static List<Song> parseAllSongsFromJSONString(Reader jsonReader) {
        try {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(jsonReader).getAsJsonArray();
            return parseSongsFromJsonArray(jsonArray);
        } catch (IllegalStateException e) {
            // The response is not a JSON array. (May be the response is too big or null
            e.printStackTrace();
        }
        return null;
    }

    public static List<Playlist> parseAllPlaylistFromJSONString(String json) {
        try {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(json).getAsJsonArray();
            return parseAllPlaylistFromJSONArray(jsonArray);
        } catch (Exception e) {
            // cannot parse playlist
            e.printStackTrace();
            return null;
        }
    }

    public static List<Integer> parseAllSongIdsFromJSONString(String json) {
        if (json != null) {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(json).getAsJsonArray();
            return parseAllSongIdsFromJSONArray(jsonArray);
        }
        return null;
    }

    public static DBVersion getDBVersionDetail(String json) {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = (JsonObject)jsonParser.parse(json);
            int no = object.get("id").getAsInt();
            Date date = new SimpleDateFormat(Config.DEFAULT_DATE_FORMAT).parse(object.get("date").getAsString());
            int number = object.get("song_number").getAsInt();
            return new DBVersion(no, date, number);
        } catch (Exception e) {
            // parse error : maybe account is not exist
            e.printStackTrace();
            return null;
        }
    }

    public static HACAccount parseAccountFromJSONString(String json) {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = (JsonObject)jsonParser.parse(json);
            String username = object.get("username").getAsString();
            String password = object.get("password").getAsString();
            String email = object.get("email").getAsString();
            String link = object.get("avatar_image_data").getAsString();
            LogUtils.LOGE(TAG, "Parse Account Detail:\n" + "Username: " + username + "\t" + password + "\t" + email);
            byte[] image = EncodingUtils.decodeDataUsingBase64(link);
            return new HACAccount(username, password, email, image);
        } catch (Exception e) {
            // parse error : maybe account is not exist
            e.printStackTrace();
            return null;
        }
    }

    ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    //endregion

    //region Get Data From Resource For Testing Purpose
    *//**
     * Test purpose (use artist.json)
     * @param context
     * @return
     *//*
    public static List<Artist> getAllArtistsFromRescource(Context context) {
        Reader reader;
        *//*InputStream stream = context.getResources()
                .openRawResource(R.raw.artist);*//*
        InputStream stream = null;
        reader = new BufferedReader(new InputStreamReader(stream), 8092);

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(reader).getAsJsonArray();

        return parseArtistsFromJsonArray(jsonArray);
    }

    *//**
     * Get all song object from resourse, contains authors, singers and chords.
     * TODO: add a JSON string as a parameter (or stream)
     *//*
    public static List<Song> getAllSongsFromResource(Context context) {
        Reader reader;

       *//* InputStream stream = context.getResources()
                .openRawResource(R.raw.update);*//*
        InputStream stream = null;

        reader = new BufferedReader(new InputStreamReader(stream), 8092);

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(reader).getAsJsonArray();

        return parseSongsFromJsonArray(jsonArray);
    }

    *//**
     * Test purpose (use chord.json)
     *//*
    public static List<Chord> getAllChordsFromResource(Context context) {
        Reader reader;
        *//*InputStream stream = context.getResources()
                .openRawResource(R.raw.chord);*//*
        InputStream stream = null;
        reader = new BufferedReader(new InputStreamReader(stream), 8092);

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(reader).getAsJsonArray();

        return parseChordsFromJsonArray(jsonArray);
    }
    //endregion

    //region Private Method for Parser
    /////////////////////////////////////////////
    /////////////// private method //////////////
    /////////////// parsing data ////////////////

    private static List<Artist> parseArtistsFromJsonArray(JsonArray jsonArray) {
        List<Artist> songs = new ArrayList<Artist>();
        for (JsonElement element : jsonArray) {
            try{
                JsonObject object = element.getAsJsonObject();
                int artistId = object.get("artist_id").getAsInt();
                String artistName = object.get("artist_name").getAsString();
                String artistAscii = object.get("artist_ascii").getAsString();
                Artist artist = new Artist(artistId, artistName, artistAscii);
                songs.add(artist);
            }
            catch (Exception e) {
                LogUtils.LOGE(TAG, element.toString() + "cannot parse to Artist");
            }
        }
        return songs;
    }

    private static List<Song> parseSongsFromJsonArray(JsonArray jsonArray) {
        List<Song> songs = new ArrayList<Song>();
        for (JsonElement element : jsonArray) {
            try{
                JsonObject object = element.getAsJsonObject();
                int songId = object.get("song_id").getAsInt();
                String title = object.get("title").getAsString();
                String link = object.get("link").getAsString();
                String content = object.get("content").getAsString();
                String lyric = object.get("firstlyric").getAsString();
                Date date = new SimpleDateFormat(Config.DEFAULT_DATE_FORMAT).parse(object.get("date").getAsString());
                Song song = new Song(Config.DEFAULT_SONG_ID, songId, title, link, content, lyric, date);

                // TrungDQ: just a little more work to get it right.
                JsonArray authorArray = object.get("authors").getAsJsonArray();
                List<Artist> authors = parseArtistsFromJsonArray(authorArray);

                JsonArray singerArray = object.get("singers").getAsJsonArray();
                List<Artist> singers = parseArtistsFromJsonArray(singerArray);

                JsonArray chordArray = object.get("chords").getAsJsonArray();
                List<Chord> chords = parseChordsFromJsonArray(chordArray);

                song.setAuthors(authors);
                song.setSingers(singers);
                song.setChords(chords);

                songs.add(song);
            }
            catch (Exception e) {
                LogUtils.LOGE(TAG, element + " cannot parse to Song: " + e.toString());
            }
        }
        return songs;
    }

    *//**
     * TODO: Update comment later
     * @param jsonArray
     * @param listner
     *//*
    public static void parseSongsFromJsonArrayNoReturn(JsonArray jsonArray, ParseListItemDoneListener listner) {
        int i = 0;
        for (JsonElement element : jsonArray) {
            try{
                JsonObject object = element.getAsJsonObject();
                int songId = object.get("song_id").getAsInt();
                String title = object.get("title").getAsString();
                String link = object.get("link").getAsString();
                String content = object.get("content").getAsString();
                String lyric = object.get("firstlyric").getAsString();
                Date date = new SimpleDateFormat(Config.DEFAULT_DATE_FORMAT).parse(object.get("date").getAsString());
                Song song = new Song(Config.DEFAULT_SONG_ID, songId, title, link, content, lyric, date);

                // TrungDQ: just a little more work to get it right.
                JsonArray authorArray = object.get("authors").getAsJsonArray();
                List<Artist> authors = parseArtistsFromJsonArray(authorArray);

                JsonArray singerArray = object.get("singers").getAsJsonArray();
                List<Artist> singers = parseArtistsFromJsonArray(singerArray);

                JsonArray chordArray = object.get("chords").getAsJsonArray();
                List<Chord> chords = parseChordsFromJsonArray(chordArray);

                song.setAuthors(authors);
                song.setSingers(singers);
                song.setChords(chords);

                listner.onParseListItemDone(song, ++i);
            }
            catch (Exception e) {
                LogUtils.LOGE(TAG, element + " cannot parse to Song: " + e.toString());
            }
        }
    }

    private static List<Chord> parseChordsFromJsonArray(JsonArray jsonArray) {
        List<Chord> chords = new ArrayList<Chord>();
        for (JsonElement element : jsonArray) {
            try{
                JsonObject object = element.getAsJsonObject();
                int chordId = object.get("chord_id").getAsInt();
                String name = object.get("name").getAsString();
                Chord chord = new Chord(chordId, name);
                chords.add(chord);
            }
            catch (Exception e) {
                LogUtils.LOGE(TAG, element + " cannot parse to chord");
            }
        }
        return chords;
    }

    private static List<Playlist> parseAllPlaylistFromJSONArray(JsonArray jsonArray) {
        List<Playlist> playlists = new ArrayList<Playlist>();
        for (JsonElement element : jsonArray) {
            try {
                JsonObject object = element.getAsJsonObject();
                int playlistId = object.get("playlist_id").getAsInt();
                String name = object.get("name").getAsString();
                String description = object.get("description").getAsString();
                Date date = new SimpleDateFormat(Config.DEFAULT_DATE_FORMAT).parse(object.get("date").getAsString());
                int isPublic = object.get("public").getAsInt();
                List<Integer> songIds = parseAllSongIdsFromJSONArray(object.get("song_ids").getAsJsonArray());
                Playlist playlist = new Playlist(playlistId, name, description, date, isPublic);
                playlist.setSongIds(songIds);
                playlists.add(playlist);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return playlists;
    }

    *//** parse all song ids from array
     * use this method for parse playlist or parse favorite
     *//*
    private static List<Integer> parseAllSongIdsFromJSONArray(JsonArray jsonArray) {
        List<Integer> ids = new ArrayList<Integer>();
        for (JsonElement element : jsonArray) {
            try {
                int id = element.getAsInt();
                ids.add(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ids;
    }
    //endregion
    */
}
