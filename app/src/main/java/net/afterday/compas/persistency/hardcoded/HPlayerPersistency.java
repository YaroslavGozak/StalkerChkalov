package net.afterday.compas.persistency.hardcoded;

import net.afterday.compas.core.player.Player;
import net.afterday.compas.persistency.player.PlayerPersistency;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by spaka on 6/14/2018.
 */
public class HPlayerPersistency implements PlayerPersistency
{
    private Map<String, Player.FRACTION> fractions = new HashMap<>();
    private Map<String, Player.COMMAND> commands = new HashMap<>();
    private Map<String, Player.INSTANTEFFECT> effects = new HashMap<>();
    private Map<String, Player.DRUGSEFFECT> drugs = new HashMap<>();
    private Map<String, Player.FRACTIONSET> fractionSets = new HashMap<>();

    public HPlayerPersistency()
    {
        setupFractions();
        setupCommands();
        setupInstantEffects();
        setupDrugEffects();
        setupFractionSets();
    }

    public Player.FRACTION getFractionByCode(String code)
    {
        if(fractions.containsKey(code))
        {
            return fractions.get(code);
        }
        return null;
    }

    @Override
    public Player.COMMAND getCommandByCode(String code)
    {
        if(commands.containsKey(code))
        {
            return commands.get(code);
        }
        return null;
    }

    @Override
    public  Player.INSTANTEFFECT getInstantEffectByCode(String code)
    {
        if(effects.containsKey(code))
        {
            return effects.get(code);
        }
        return null;
    }

    @Override
    public  Player.DRUGSEFFECT getDrugEffectByCode(String code)
    {
        if(drugs.containsKey(code))
        {
            return drugs.get(code);
        }
        return null;
    }

    @Override
    public Player.FRACTIONSET getFractionSetByCode(String code)
    {
        if(fractionSets.containsKey(code))
        {
            return fractionSets.get(code);
        }
        return null;
    }

    private void setupFractions()
    {
        fractions.put("S4SINS_Monolith", Player.FRACTION.MONOLITH); //Режим монолитовца
        fractions.put("S4SINS_GameMaster", Player.FRACTION.GAMEMASTER); //Режим игромастера
        fractions.put("S4SINS_Stalker", Player.FRACTION.STALKER); //Режим обычного игрока
        fractions.put("S4SINS_Dark", Player.FRACTION.DARKEN); //Режим Тёмного

    }
    private void setupCommands()
    {
        commands.put("S4SINS_Kill", Player.COMMAND.KILL); //Убить игрока
        commands.put("S4SINS_Revive", Player.COMMAND.REVIVE); //Оживить игрока

    }
    private void setupInstantEffects()
    {
        effects.put("S4SINS_AddRad1", Player.INSTANTEFFECT.ADDRAD1);
        effects.put("S4SINS_AddRad3", Player.INSTANTEFFECT.ADDRAD3);
        effects.put("S4SINS_AddHealth10", Player.INSTANTEFFECT.ADDHEALTH10);
        effects.put("S4SINS_AddHealth20", Player.INSTANTEFFECT.ADDHEALTH20);
        effects.put("S4SINS_AddHealth30", Player.INSTANTEFFECT.ADDHEALTH30);
        effects.put("S4SINS_RemoveRad1", Player.INSTANTEFFECT.REMOVERAD1);
        effects.put("S4SINS_RemoveRad3", Player.INSTANTEFFECT.REMOVERAD3);
        effects.put("S4SINS_RemoveHealth10", Player.INSTANTEFFECT.REMOVEHEALTH10);
        effects.put("S4SINS_RemoveHealth20", Player.INSTANTEFFECT.REMOVEHEALTH20);
        effects.put("S4SINS_RemoveHealth30", Player.INSTANTEFFECT.REMOVEHEALTH30);
    }

    private void setupDrugEffects()
    {
        drugs.put("S4SINS_Drug1", Player.DRUGSEFFECT.DRUG1);
        drugs.put("S4SINS_Drug2", Player.DRUGSEFFECT.DRUG2);
        drugs.put("S4SINS_Drug3", Player.DRUGSEFFECT.DRUG3);
        drugs.put("S4SINS_Drug4", Player.DRUGSEFFECT.DRUG4);
        drugs.put("S4SINS_Drug5", Player.DRUGSEFFECT.DRUG5);
    }

    private void setupFractionSets()
    {
        fractionSets.put("S4SINS_Novice", Player.FRACTIONSET.STALKER_NOVICE_SET);
        fractionSets.put("S4SINS_Experienced", Player.FRACTIONSET.STALKER_EXP_SET);
        fractionSets.put("S4SINS_Army", Player.FRACTIONSET.ARMY_SET);
        fractionSets.put("S4SINS_Gang", Player.FRACTIONSET.BANDIT_SET);
        fractionSets.put("S4SINS_Freedom", Player.FRACTIONSET.FREEDOM_SET);
        fractionSets.put("S4SINS_Duty", Player.FRACTIONSET.DUTY_SET);
        fractionSets.put("S4SINS_Merc", Player.FRACTIONSET.MERC_SET);
        fractionSets.put("S4SINS_ClearSky", Player.FRACTIONSET.CLEARSKY_SET);
        fractionSets.put("S4SINS_Monolith", Player.FRACTIONSET.MONOLITH_SET);
        fractionSets.put("S4SINS_Science", Player.FRACTIONSET.SCIENCE_SET);
    }
}
