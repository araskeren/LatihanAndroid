package id.web.damisbachtiar17.word_list_sql_with_content_provider;

public class WordItem {
    private int mId;
    private String mWord;

    public WordItem() {}

    public int getId() {
        return this.mId;
    }

    public String getWord() {
        return this.mWord;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public void setWord(String word) {
        this.mWord = word;
    }
}
