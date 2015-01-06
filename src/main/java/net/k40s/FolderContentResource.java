package net.k40s;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceStreamResource;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.resource.FileResourceStream;

public class FolderContentResource implements IResource {
  private final File rootFolder;

  public FolderContentResource(File rootFolder) {

    this.rootFolder = rootFolder;
  }

  public void respond(Attributes attributes) {

    PageParameters parameters = attributes.getParameters();
    String fileName = parameters.get(0).toString();
    File file = new File(rootFolder, fileName);
    FileResourceStream fileResourceStream = new FileResourceStream(file);
    ResourceStreamResource resource = new ResourceStreamResource(fileResourceStream);
    resource.respond(attributes);
  }
}