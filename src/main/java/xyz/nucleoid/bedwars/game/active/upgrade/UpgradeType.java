package xyz.nucleoid.bedwars.game.active.upgrade;

import xyz.nucleoid.plasmid.shop.Cost;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import moriyashiine.enchancement.common.registry.ModEnchantments;

import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public final class UpgradeType<T extends Upgrade> {
    public static final UpgradeType<ArmorUpgrade> ARMOR = new UpgradeType<ArmorUpgrade>()
            .addLevel(ArmorUpgrade.LEATHER)
            .addLevel(ArmorUpgrade.IRON)
            .addLevel(ArmorUpgrade.DIAMOND);

    public static final UpgradeType<WeaponUpgrade> SWORD = new UpgradeType<WeaponUpgrade>()
            .addLevel(new WeaponUpgrade(Items.WOODEN_SWORD, Cost.no()))
            .addLevel(new WeaponUpgrade(Items.STONE_SWORD, Cost.ofIron(12)))
            .addLevel(new WeaponUpgrade(Items.IRON_SWORD, Cost.ofGold(6)))
            .addLevel(new WeaponUpgrade(Items.DIAMOND_SWORD, Cost.ofEmeralds(3)));

    public static final UpgradeType<WeaponUpgrade> PICKAXE = new UpgradeType<WeaponUpgrade>()
            .addLevel(new WeaponUpgrade(Items.WOODEN_PICKAXE, Cost.ofIron(8)))
            .addLevel(new WeaponUpgrade(Items.STONE_PICKAXE, Cost.ofIron(12)))
            .addLevel(new WeaponUpgrade(Items.IRON_PICKAXE, Cost.ofGold(4)))
            .addLevel(new WeaponUpgrade(diamondTool(Items.DIAMOND_PICKAXE), Cost.ofGold(12)));

    public static final UpgradeType<WeaponUpgrade> AXE = new UpgradeType<WeaponUpgrade>()
            .addLevel(new WeaponUpgrade(Items.WOODEN_AXE, Cost.ofIron(8)))
            .addLevel(new WeaponUpgrade(Items.STONE_AXE, Cost.ofIron(12)))
            .addLevel(new WeaponUpgrade(Items.IRON_AXE, Cost.ofGold(4)))
            .addLevel(new WeaponUpgrade(diamondTool(Items.DIAMOND_AXE), Cost.ofGold(8)));

    public static final UpgradeType<WeaponUpgrade> SHEARS = new UpgradeType<WeaponUpgrade>()
            .addLevel(new WeaponUpgrade(Items.SHEARS, Cost.ofIron(40)));

    public static final UpgradeType<WeaponUpgrade> TRIDENT = new UpgradeType<WeaponUpgrade>()
            .addLevel(new WeaponUpgrade(Items.TRIDENT, Cost.ofGold(8)))
            .addLevel(new WeaponUpgrade(trident(Items.TRIDENT, 2), Cost.ofDiamonds(1)));

    private static ItemStack diamondTool(Item item) {
        ItemStack stack = new ItemStack(item);
        stack.addEnchantment(Enchantments.EFFICIENCY, 2);
        return stack;
    }

    private static ItemStack trident(Item item, int level) {
        ItemStack stack = new ItemStack(item);
        if (level>=2) {stack.addEnchantment(ModEnchantments.LEECH, 1);};
        return stack;
    }

    private final List<T> levels = new ArrayList<>();

    public UpgradeType<T> addLevel(T level) {
        this.levels.add(level);
        return this;
    }

    @Nullable
    public T forLevel(int level) {
        if (!this.containsLevel(level)) {
            return null;
        }
        return this.levels.get(level);
    }

    public boolean containsLevel(int level) {
        return level >= 0 && level < this.levels.size();
    }
}
