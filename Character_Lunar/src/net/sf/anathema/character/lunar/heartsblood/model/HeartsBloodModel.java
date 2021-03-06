package net.sf.anathema.character.lunar.heartsblood.model;

import net.sf.anathema.character.generic.additionaltemplate.*;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.removableentry.model.AbstractRemovableEntryModel;
import net.sf.anathema.character.library.removableentry.presenter.RemovableEntryChangeAdapter;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodTemplate;
import net.sf.anathema.character.lunar.heartsblood.presenter.IAnimalForm;
import net.sf.anathema.character.lunar.heartsblood.presenter.IHeartsBloodModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.lang.StringUtilities;

public class HeartsBloodModel extends AbstractRemovableEntryModel<IAnimalForm> implements IAdditionalModel, IHeartsBloodModel {

  private String currentName;
  private int currentStrength;
  private int currentDexterity;
  private int currentStamina;
  private int currentAppearance;
  private final ICharacterModelContext context;

  public HeartsBloodModel(ICharacterModelContext context) {
    this.context = context;
  }

  @Override
  public String getTemplateId() {
    return HeartsBloodTemplate.TEMPLATE_ID;
  }

  @Override
  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  @Override
  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new NullAdditionalModelBonusPointCalculator();
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    addModelChangeListener(new RemovableEntryChangeAdapter<IAnimalForm>(listener));
  }

  @Override
  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }

  @Override
  public void setCurrentName(String newValue) {
    this.currentName = newValue;
    fireEntryChanged();
  }

  @Override
  public void setCurrentStrength(int newValue) {
    this.currentStrength = newValue;
  }

  @Override
  public void setCurrentDexterity(int newValue) {
    this.currentDexterity = newValue;
  }

  @Override
  public void setCurrentStamina(int newValue) {
    this.currentStamina = newValue;
  }

  @Override
  public void setCurrentAppearance(int newValue) {
    this.currentAppearance = newValue;
  }

  @Override
  protected boolean isEntryAllowed() {
    return !StringUtilities.isNullOrEmpty(currentName);
  }

  @Override
  protected IAnimalForm createEntry() {
    return new AnimalForm(currentName, currentStrength, currentDexterity, currentStamina, currentAppearance,
            context.getBasicCharacterContext().isExperienced());
  }

  @Override
  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }
}