package net.sf.anathema.character.generic.impl.bootjob;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.tree.FileExporter;
import net.sf.anathema.framework.repository.tree.RepositoryZipPathCreator;
import net.sf.anathema.lib.resources.IResources;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RepositoryBackup {
  private IResources resources;
  private IAnathemaModel model;

  public RepositoryBackup(IResources resources, IAnathemaModel model) {
    this.resources = resources;
    this.model = model;
  }

  public void backupRepository() {
    try {
      IRepository repository = model.getRepository();
      IItemType[] itemTypes = model.getItemTypeRegistry().getAllItemTypes();
      CleanupExportModel exportModel = new CleanupExportModel(itemTypes, repository);
      if (exportModel.getPrintNameFilesInSelection().length == 0) {
        return;
      }
      RepositoryZipPathCreator creator = new RepositoryZipPathCreator(repository.getRepositoryPath());
      Path saveFile = getSaveFile();
      new FileExporter(creator, exportModel, resources).exportToZip(saveFile);
    } catch (IOException e) {
      throw new RuntimeException(
              "Could not back up repository before launch. Please create a copy and delete all 1E characters manually.",
              e);
    }
  }

  private Path getSaveFile() {
    IRepository repository = model.getRepository();
    String version = new Version(resources).asString();
    return Paths.get(repository.getRepositoryPath()).resolve("BackupForFirstLaunchOf" + version + ".zip");
  }
}