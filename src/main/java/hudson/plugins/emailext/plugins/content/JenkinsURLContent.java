package hudson.plugins.emailext.plugins.content;

import hudson.Util;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import hudson.plugins.emailext.plugins.EmailToken;
import hudson.plugins.emailext.ExtendedEmailPublisher;
import java.io.IOException;
import hudson.model.Hudson;

import org.jenkinsci.plugins.tokenmacro.DataBoundTokenMacro;
import org.jenkinsci.plugins.tokenmacro.MacroEvaluationException;

@EmailToken
public class JenkinsURLContent extends DataBoundTokenMacro {

    private static final String MACRO_NAME = "JENKINS_URL";

    @Override
    public boolean acceptsMacroName(String macroName) {
        return macroName.equals(MACRO_NAME) || macroName.equals("HUDSON_URL");
    }

    public String getHelpText() {
        return "Displays the URL to the Hudson server. (You can change this on the system configuration page.)";
    }

    @Override
    public String evaluate(AbstractBuild<?, ?> context, TaskListener listener, String macroName)
            throws MacroEvaluationException, IOException, InterruptedException {
        // HUDSON-6193 - Only override the global URL if we should override global settings
        String jenkinsUrl = Hudson.getInstance().getRootUrl();
        if (ExtendedEmailPublisher.DESCRIPTOR.getOverrideGlobalSettings()) {
            jenkinsUrl = ExtendedEmailPublisher.DESCRIPTOR.getHudsonUrl();
        }

        if (jenkinsUrl == null) {
            return "";
        }
        if (!jenkinsUrl.endsWith("/")) {
            jenkinsUrl += "/";
        }

        return Util.encode(jenkinsUrl);
    }
}
