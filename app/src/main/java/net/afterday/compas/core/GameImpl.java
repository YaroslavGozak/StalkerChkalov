package net.afterday.compas.core;

import net.afterday.compas.core.fraction.Fraction;
import net.afterday.compas.core.gameState.Frame;
import net.afterday.compas.core.gameState.FrameImpl;
import net.afterday.compas.core.influences.InfluencesPack;
import net.afterday.compas.core.inventory.Inventory;
import net.afterday.compas.core.inventory.InventoryImpl;
import net.afterday.compas.core.inventory.items.Events.DropItem;
import net.afterday.compas.core.inventory.items.Item;
import net.afterday.compas.core.player.Player;
import net.afterday.compas.core.player.PlayerImpl;
import net.afterday.compas.core.serialization.Serializer;
import net.afterday.compas.persistency.PersistencyProvider;
import net.afterday.compas.persistency.items.ItemDescriptor;
import net.afterday.compas.persistency.items.ItemsPersistency;

import java.util.List;
import java.util.Map;

public class GameImpl implements Game
{
    private PlayerImpl mPlayer;
    private Controls controls;
    private PersistencyProvider persistencyProvider;


    private static final String itemKurtkaCode = "S5CURSED_LeatherSuit";
    private static final String itemZariaSuitCode = "S5CURSED_ZariaSuit";
    private static final String itemFreedomSuitCode = "S5CURSED_FreedomSuit";
    private static final String itemBanditSuitCode = "S5CURSED_BanditSuit";
    private static final String itemMercSuitCode = "S5CURSED_MercSuit";
    private static final String itemDutySuitCode = "S5CURSED_DolgSuit";
    private static final String itemArmySuitCode = "S5CURSED_BerillSuit";
    private static final String itemScienceSuitCode = "S5CURSED_EkologSuit";
    private static final String itemClearSkySuitCode = "S5CURSED_ClearSkySuit";
    private static final String itemMonolithSuitCode = "S5CURSED_MonolithSuit";

    private static final String itemBandageCode = "S5CURSED_Bandage";
    private static final String itemMedkitCode = "S5CURSED_MedKit";
    private static final String itemArmyMedkitCode = "S5CURSED_ArmyMedKit";
    private static final String itemScienceMedkitCode = "S5CURSED_SienceMedKit";
    private static final String itemAntiradCode = "S5CURSED_AntiRad";
    private static final String itemAntidotCode = "S5CURSED_IP2";
    private static final String itemRadprotectCode = "S5CURSED_B190";
    private static final String itemPsiBlokadaCode = "S5CURSED_PsiMeds";

    private static final String itemBarvinokCode = "S5CURSED_Barvinok";
    private static final String itemRadvinokCode = "S5CURSED_Radivinok";
    private static final String itemMenvinokCode = "S5CURSED_Menvinok";
    private static final String itemVodkaCode = "S5CURSED_Vodka";
    private static final String itemEnergetikCode = "S5CURSED_EnStalker";

    private static final String itemKnifeCode = "S5CURSED_Knife";

    private static final String itemRespiratorCode = "S5CURSED_Respirator";
    private static final String itemGasMaskCode = "S5CURSED_GasMask";

    private static final String itemPdaCode = "S5CURSED_PDAwithInfo";

    public GameImpl(PersistencyProvider persistencyProvider, Serializer serializer)
    {
        this.controls = controls;
        this.persistencyProvider = persistencyProvider;
        mPlayer = new PlayerImpl(new InventoryImpl(persistencyProvider.getItemsPersistency(), serializer), serializer);
    }

    @Override
    public Frame start()
    {
        return new FrameImpl(mPlayer.getPlayerProps());
    }

    public Frame acceptInfluences(InfluencesPack influencesPack)
    {
        return mPlayer.acceptInfluences(influencesPack);
    }

    @Override
    public Player getPlayer()
    {
        return mPlayer;
    }

    @Override
    public Inventory getInventory()
    {
        return mPlayer.getInventory();
    }

    @Override
    public boolean acceptCode(String code)
    {
        ItemDescriptor itemDesc = persistencyProvider.getItemsPersistency().getItemForCode(code);
        if(itemDesc != null)
        {
            return mPlayer.addItem(itemDesc, code);
        }
        Player.FRACTION fraction = persistencyProvider.getPlayerPersistency().getFractionByCode(code);
        if(fraction != null)
        {
            mPlayer.setFraction(fraction);
            return true;
        }
        Player.COMMAND command = persistencyProvider.getPlayerPersistency().getCommandByCode(code);
        if(command != null)
        {
            switch (command)
            {
                case KILL: mPlayer.setState(Player.STATE.DEAD_BURER); return true;
                case REVIVE: mPlayer.reborn(); return true;
            }
        }

        Player.INSTANTEFFECT iEffect = persistencyProvider.getPlayerPersistency().getInstantEffectByCode(code);
        if(iEffect != null)
        {
             mPlayer.instantEffect(iEffect);
             return true;
        }

        Player.DRUGSEFFECT dEffect = persistencyProvider.getPlayerPersistency().getDrugEffectByCode(code);
        if(dEffect != null)
        {
            mPlayer.drugEffect(dEffect);
            return true;
        }

        Player.FRACTIONSET fractionSet = persistencyProvider.getPlayerPersistency().getFractionSetByCode(code);
        if(fractionSet != null)
        {

            switch (fractionSet)
            {
                case STALKER_NOVICE_SET:
                    // Куртка, Бинт, Нож, Респиратор
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemKurtkaCode), itemKurtkaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemBandageCode), itemBandageCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemKnifeCode), itemKnifeCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemRespiratorCode),itemRespiratorCode);
                    break;
                case STALKER_EXP_SET:
                    // 100 опыта, нож, противогаз, аптечка, енергетик, водка, Заря
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemZariaSuitCode), itemZariaSuitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemVodkaCode), itemVodkaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemEnergetikCode), itemEnergetikCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemMedkitCode), itemMedkitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemGasMaskCode), itemGasMaskCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemKnifeCode), itemKnifeCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    break;
                case ARMY_SET:
                    // 100 опыта, нож, противогаз, армійська аптечка (2), антірад, псіблокада, Берилл
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemGasMaskCode), itemGasMaskCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemKnifeCode), itemKnifeCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemArmySuitCode), itemArmySuitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemArmyMedkitCode), itemArmyMedkitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemArmyMedkitCode), itemArmyMedkitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemAntiradCode), itemAntiradCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPsiBlokadaCode), itemPsiBlokadaCode);
                    break;
                case BANDIT_SET:
                    // 100 опыта, нож, противогаз, Костюм банды, водка (3), аптечка
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemVodkaCode), itemVodkaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemVodkaCode), itemVodkaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemVodkaCode), itemVodkaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemMedkitCode), itemMedkitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemBanditSuitCode), itemBanditSuitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemGasMaskCode), itemGasMaskCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemKnifeCode), itemKnifeCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    break;
                case FREEDOM_SET:
                    // 100 опыта, нож, противогаз, радвинок, менвинок, барвинок, аптечка, кост. Свободы
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemGasMaskCode), itemGasMaskCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemKnifeCode), itemKnifeCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemFreedomSuitCode), itemFreedomSuitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemMenvinokCode), itemMenvinokCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemRadvinokCode), itemRadvinokCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemBarvinokCode), itemBarvinokCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemMedkitCode), itemMedkitCode);
                    break;
                case DUTY_SET:
                    // 100 опыта, нож, противогаз, антірад, армійська аптечка (2), костюм долга
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemGasMaskCode), itemGasMaskCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemKnifeCode), itemKnifeCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemDutySuitCode), itemDutySuitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemAntiradCode), itemAntiradCode );
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemArmyMedkitCode), itemArmyMedkitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemArmyMedkitCode), itemArmyMedkitCode);
                    break;
                case CLEARSKY_SET:
                    // 100 опыта, нож, противогаз, наукова аптечка, барвінок, менвінок, радвінок, костюм ЧН
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemGasMaskCode), itemGasMaskCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemKnifeCode), itemKnifeCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemMenvinokCode), itemMenvinokCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemRadvinokCode), itemRadvinokCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemBarvinokCode), itemBarvinokCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemClearSkySuitCode), itemClearSkySuitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemScienceMedkitCode), itemScienceMedkitCode);
                    break;
                case MERC_SET:
                    // 100 опыта, нож, противогаз, антірад, радпротектор, енергетик, аптечка, костюм Найманця
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemGasMaskCode), itemGasMaskCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemKnifeCode), itemKnifeCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemMercSuitCode), itemMercSuitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemMedkitCode), itemMedkitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemAntiradCode), itemAntiradCode );
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemRadprotectCode), itemRadprotectCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemEnergetikCode), itemEnergetikCode);
                    break;
                case MONOLITH_SET:
                    // 100 опыта, нож, противогаз, антірад, антідот, наукова аптечка, костюм Моноліта
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemGasMaskCode), itemGasMaskCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemKnifeCode), itemKnifeCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemMonolithSuitCode), itemMonolithSuitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemAntiradCode), itemAntiradCode );
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemAntidotCode), itemAntidotCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemScienceMedkitCode), itemScienceMedkitCode);
                    break;
                case SCIENCE_SET:
                    // 100 опыта, противогаз, наукова аптечка (2), псі блокада, Еколог
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemGasMaskCode), itemGasMaskCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPdaCode), itemPdaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemScienceMedkitCode), itemScienceMedkitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemScienceMedkitCode), itemScienceMedkitCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemPsiBlokadaCode), itemPsiBlokadaCode);
                    mPlayer.addItem(persistencyProvider.getItemsPersistency().getItemForCode(itemScienceSuitCode), itemScienceSuitCode);
                    break;
            }

            return true;
        }

        return false;
    }

    @Override
    public Frame useItem(Item item)
    {
        return mPlayer.useItem(item);
    }

    public Frame dropItem(DropItem dropItem)
    {
        return null;
    }

    public Frame changeFraction(Fraction fraction)
    {
        return null;
    }
}
