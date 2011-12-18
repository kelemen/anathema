package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.Graphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.IPdfTableEncoder;

public class PdfCombatStatsEncoder extends AbstractPdfEncoder implements IBoxContentEncoder {

  private final static float PADDING = 3;

  private final BaseFont baseFont;
  private final IPdfTableEncoder combatRulesEncoder;
  private final IPdfContentEncoder combatValueEncoder;

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public PdfCombatStatsEncoder(IPdfTableEncoder combatRulesEncoder, IPdfContentEncoder combatValueEncoder, BaseFont baseFont) {
    this.baseFont = baseFont;
    this.combatRulesEncoder = combatRulesEncoder;
    this.combatValueEncoder = combatValueEncoder;
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "Combat"; //$NON-NLS-1$
  }

  public void encode(Graphics graphics, ReportContent reportContent) throws DocumentException {
    float height = combatValueEncoder.encode(graphics.getDirectContent(), reportContent.getCharacter(), graphics.getBounds());
    Bounds ruleBounds = new Bounds(graphics.getBounds().x, graphics.getBounds().y, graphics.getBounds().width,
      graphics.getBounds().height - height - PADDING);
    encodeRules(graphics.getDirectContent(), reportContent, ruleBounds);
  }

  private void encodeRules(PdfContentByte directContent, ReportContent content, Bounds bounds) throws DocumentException {
    combatRulesEncoder.encodeTable(directContent, content, bounds);
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}