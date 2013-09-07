/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hudson.plugins.emailext;

import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.Descriptor;
import hudson.model.Project;
import hudson.plugins.emailext.plugins.OnlyRegressionsTest;
import hudson.tasks.Builder;
import hudson.util.DescribableList;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import org.jvnet.hudson.test.TestBuilder;

/**
 *
 * @author Bob Foster
 */
public class ProjectUtils {

    public static void addBuilder(Project project, Builder builder) throws IOException {
        DescribableList<Builder, Descriptor<Builder>> list = project.getBuildersList();
        list.add(builder);
        project.setBuilders(list);
    }
    
    private static class BuildersList extends DescribableList<Builder, Descriptor<Builder>> {
    }
    
    private static final DescribableList<Builder, Descriptor<Builder>> EMPTY_BUILDERS =
            new BuildersList();
    
    public static void clearBuilders(Project project) {
        project.setBuilders(EMPTY_BUILDERS);
    }
}
