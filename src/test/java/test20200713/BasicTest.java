package test20200713;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.dmn.core.internal.utils.DMNRuntimeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicTest {

    public static final Logger LOG = LoggerFactory.getLogger(BasicTest.class);

    @Test
    public void test() throws IOException {
        DMNRuntime runtime = DMNRuntimeBuilder.fromDefaults()
                                              .buildConfiguration()
                                              .fromClasspathResource("/a.dmn", BasicTest.class)
                                              .getOrElseThrow(RuntimeException::new);
        DMNModel model0 = runtime.getModels().get(0);
        DMNContext context = runtime.newContext();
        context.set("name", "John Doe");
        DMNResult evaluateAll = runtime.evaluateAll(model0, context);
        System.out.println(evaluateAll.getContext());
        assertEquals("Hello, John Doe", evaluateAll.getDecisionResultByName("Decision-1").getResult());
    }
}