//package edu.evgen.shawarma;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.stereotype.Component;
//
//import static edu.evgen.shawarma.Colors.GREEN;
//import static edu.evgen.shawarma.Colors.RESET;
//@Component
//public class myBPP implements BeanPostProcessor {
//    int i = 0;
//    /**
//     * Apply this {@code BeanPostProcessor} to the given new bean instance <i>before</i> any bean
//     * initialization callbacks (like InitializingBean's {@code afterPropertiesSet}
//     * or a custom init-method). The bean will already be populated with property values.
//     * The returned bean instance may be a wrapper around the original.
//     * <p>The default implementation returns the given {@code bean} as-is.
//     *
//     * @param bean     the new bean instance
//     * @param beanName the name of the bean
//     * @return the bean instance to use, either the original or a wrapped one;
//     * if {@code null}, no subsequent BeanPostProcessors will be invoked
//     * @throws BeansException in case of errors
//     * @see InitializingBean#afterPropertiesSet
//     */
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println(GREEN + beanName + i++ + RESET);
//        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
//    }
//
//    /**
//     * Apply this {@code BeanPostProcessor} to the given new bean instance <i>after</i> any bean
//     * initialization callbacks (like InitializingBean's {@code afterPropertiesSet}
//     * or a custom init-method). The bean will already be populated with property values.
//     * The returned bean instance may be a wrapper around the original.
//     * <p>In case of a FactoryBean, this callback will be invoked for both the FactoryBean
//     * instance and the objects created by the FactoryBean (as of Spring 2.0). The
//     * post-processor can decide whether to apply to either the FactoryBean or created
//     * objects or both through corresponding {@code bean instanceof FactoryBean} checks.
//     * <p>This callback will also be invoked after a short-circuiting triggered by a
//     * {@link InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation} method,
//     * in contrast to all other {@code BeanPostProcessor} callbacks.
//     * <p>The default implementation returns the given {@code bean} as-is.
//     *
//     * @param bean     the new bean instance
//     * @param beanName the name of the bean
//     * @return the bean instance to use, either the original or a wrapped one;
//     * if {@code null}, no subsequent BeanPostProcessors will be invoked
//     * @throws BeansException in case of errors
//     * @see InitializingBean#afterPropertiesSet
//     * @see FactoryBean
//     */
////    @Override
////    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
////        System.out.println(GREEN + beanName + RESET);
////        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
////    }
//}
