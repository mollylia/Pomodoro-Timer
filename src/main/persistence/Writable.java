package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();

    // EFFECTS: returns this as a string
    String toString();
}
