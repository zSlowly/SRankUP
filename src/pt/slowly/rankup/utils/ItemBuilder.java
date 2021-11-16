/*     */ package pt.slowly.rankup.utils;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemBuilder
/*     */   implements ItemSpecification<ItemBuilder>
/*     */ {
/*     */   protected ItemMeta itemMeta;
/*     */   protected ItemStack itemStack;
/*     */   protected Material material;
/*     */   protected int quantity;
/*     */   protected int data;
/*     */   
/*     */   public ItemBuilder(Material material) {
/*  26 */     this.material = material;
/*  27 */     this.itemStack = new ItemStack(material, 1);
/*  28 */     this.itemMeta = this.itemStack.getItemMeta();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemBuilder(Material material, int quantity) {
/*  33 */     this.material = material;
/*  34 */     this.quantity = quantity;
/*  35 */     this.itemStack = new ItemStack(material, quantity);
/*  36 */     this.itemMeta = this.itemStack.getItemMeta();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemBuilder(Material material, int quantity, int data) {
/*  41 */     this.material = material;
/*  42 */     this.quantity = quantity;
/*  43 */     this.data = data;
/*  44 */     this.itemStack = new ItemStack(material, quantity, (short)data);
/*  45 */     this.itemMeta = this.itemStack.getItemMeta();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemBuilder setDisplayName(String name) {
/*  51 */     this.itemMeta.setDisplayName(name.replace("&", "§"));
/*  52 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemBuilder setLore(List<String> lore) {
/*  59 */     this.itemMeta.setLore((List)lore.stream().map(x -> x.replace("&", "§")).collect(Collectors.toList()));
/*  60 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemBuilder setLore(String... lore) {
/*  66 */     List<String> list = Arrays.asList(lore);
/*  67 */     this.itemMeta.setLore((List)list.stream().map(x -> x.replace("&", "§")).collect(Collectors.toList()));
/*  68 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemBuilder unbreakable() {
/*  74 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemBuilder addEnchantment(Enchantment enchantment, int strength) {
/*  81 */     this.itemMeta.addEnchant(enchantment, strength, true);
/*  82 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemBuilder hideAllFlags() {
/*  89 */     this.itemMeta.addItemFlags(ItemFlag.values());
/*  90 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemBuilder hideFlag(ItemFlag itemFlag) {
/*  97 */     this.itemMeta.addItemFlags(new ItemFlag[] { itemFlag });
/*  98 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack build() {
/* 104 */     this.itemStack.setItemMeta(this.itemMeta);
/* 105 */     return this.itemStack;
/*     */   }
/*     */ }


/* Location:              /home/diogo/Desktop/Coisas/Projetos/MinecraftPlugins/D_RankUP.jar!/pt/dioguin/rankup/utils/ItemBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */