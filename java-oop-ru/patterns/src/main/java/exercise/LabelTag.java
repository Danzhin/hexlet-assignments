package exercise;

// BEGIN
public class LabelTag implements TagInterface {

    private String message;
    private TagInterface tag;

    public LabelTag(String message, TagInterface tag) {
        this.message = message;
        this.tag = tag;
    }

    @Override
    public String render() {
        return "<label>" + message + tag.render() + "</label>";
    }

}
// END
