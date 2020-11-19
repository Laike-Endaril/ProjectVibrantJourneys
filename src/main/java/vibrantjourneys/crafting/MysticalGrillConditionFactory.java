package vibrantjourneys.crafting;

import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import vibrantjourneys.util.PVJConfig;

import java.util.function.BooleanSupplier;

public class MysticalGrillConditionFactory implements IConditionFactory
{
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json)
    {
        boolean value = JsonUtils.getBoolean(json, "value", true);
        return () -> PVJConfig.master.enableMysticalGrill == value;
    }

}
