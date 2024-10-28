package exercise;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String body;
    private List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = children != null ? children : new ArrayList<>();
    }

    @Override
    public String toString() {
        String childrenString = children.stream()
            .map(Tag::toString)
            .collect(Collectors.joining());
        return String.format("<%s%s>%s%s</%s>", name, getAttributesString(), body, childrenString, name);
    }
}
// END
