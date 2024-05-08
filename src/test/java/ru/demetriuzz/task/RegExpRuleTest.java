package ru.demetriuzz.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class RegExpRuleTest {

    @Test
    void branchRule() {
        String regExpBranchName = "^(feature|bugfix|hotfix|test|support|docs)\\/[A-Z]+\\-[0-9]+\\-[a-zA-Z0-9\\-]+$|^release\\/\\d+[\\.\\d+]*$|^rc\\/\\d+[\\.\\d+]*$|^master|^revert-.*$";
        var branch = Pattern.compile(regExpBranchName);

        Assertions.assertFalse(branch.matcher("").matches());
        Assertions.assertFalse(branch.matcher("feature").matches());
        Assertions.assertFalse(branch.matcher("feature/IXXI-1111").matches());
        Assertions.assertFalse(branch.matcher("feature/IXXI-1111-веточка").matches());
        Assertions.assertTrue(branch.matcher("feature/IXXI-1111-xyz").matches());
    }

    @Test
    void commitRule() {
        String regExpCommitMessage = "(^Initial commit)|(^Merge branch.*)|(^Revert.*)|(^build|chore|ci|docs|feature|fix|perf|refactor|revert|style|test)(\\(([\\w-]+)\\))?!?: (\\[[0-9A-Z\\-]+\\])([\\w ,'.:-]+)(([\\r\\n]+)([\\w\\s ,'.\\[\\]-]+)([\\r\\n]+(([\\w -]+): ([\\w -]+)))+)?";
        var commit = Pattern.compile(regExpCommitMessage);

        Assertions.assertFalse(commit.matcher("").matches());
        Assertions.assertFalse(commit.matcher("feature").matches());
        Assertions.assertFalse(commit.matcher("feature:").matches());
        Assertions.assertFalse(commit.matcher("feature: [IXXI-1111]").matches());
        Assertions.assertFalse(commit.matcher("feature: [IXXI-1111] раз два").matches());
        Assertions.assertTrue(commit.matcher("feature: [IXXI-1111] xyz").matches());
    }

}
