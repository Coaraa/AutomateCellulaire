package rules;

import com.google.gson.*;

import java.lang.reflect.Type;

public class RuleDeserializer implements JsonDeserializer<GameRule> {
    @Override
    public GameRule deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        JsonObject root = jsonElement.getAsJsonObject();
        String name = root.get("name").getAsString();

        Class<? extends GameRule> ruleClass = switch (name) {
            case "somme" -> SumRule.class;
            case "table" -> TabRule.class;
            case "majority" -> MajRule.class;
            case "average" -> AvgRule.class;
            case "direction" -> DirRule.class;
            default -> throw new UnsupportedOperationException("Unknown rule `" + name + "`");
        };

        return ctx.deserialize(root, ruleClass);
    }
}
