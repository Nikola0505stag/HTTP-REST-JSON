package gson;

import com.google.gson.annotations.SerializedName;

public record DevManager(String name, @SerializedName("unit") String department, int level) {}
