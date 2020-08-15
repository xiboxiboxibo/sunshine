package com.xibo.util;//package com.xibo.util;
//
//import com.sun.source.tree.Tree;
//import com.sun.tools.javac.api.JavacTrees;
//import com.sun.tools.javac.processing.JavacProcessingEnvironment;
//import com.sun.tools.javac.tree.JCTree;
//import com.sun.tools.javac.tree.TreeMaker;
//import com.sun.tools.javac.tree.TreeTranslator;
//import com.sun.tools.javac.util.Context;
//import com.sun.tools.javac.util.Names;
//import com.xibo.annotation.Getter;
//
//import javax.annotation.processing.AbstractProcessor;
//import javax.annotation.processing.Messager;
//import javax.annotation.processing.ProcessingEnvironment;
//import javax.annotation.processing.RoundEnvironment;
//import javax.lang.model.element.Element;
//import javax.lang.model.element.TypeElement;
//import javax.tools.Diagnostic;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
///**
// * 注解处理器
// */
//public class MyAnnotationProcessor extends AbstractProcessor {
//
//    private Messager messager;
//    private JavacTrees trees;
//    private TreeMaker treeMaker;
//    private Names names;
//
//    @Override
//    public synchronized void init(ProcessingEnvironment processingEnv) {
//        super.init(processingEnv);
//        this.messager = processingEnv.getMessager();
//        this.trees = JavacTrees.instance(processingEnv);
//        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
//        this.treeMaker = TreeMaker.instance(context);
//        this.names = Names.instance(context);
//    }
//
//    @Override
//    public synchronized boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(Getter.class);
//        set.forEach(element -> {
//            JCTree jcTree = trees.getTree(element);
//            jcTree.accept(new TreeTranslator() {
//                @Override
//                public void visitClassDef(JCTree.JCClassDecl jcClassDecl) {
//                    List<JCTree.JCVariableDecl> jcVariableDeclList = new ArrayList<>();
//
//                    for (JCTree tree : jcClassDecl.defs) {
//                        if (tree.getKind().equals(Tree.Kind.VARIABLE)) {
//                            JCTree.JCVariableDecl jcVariableDecl = (JCTree.JCVariableDecl) tree;
//                            jcVariableDeclList.add(jcVariableDecl);
//                        }
//                    }
//
//                    jcVariableDeclList.forEach(jcVariableDecl -> {
//                        messager.printMessage(Diagnostic.Kind.NOTE, jcVariableDecl.getName() + " has been processed");
//                        jcClassDecl.defs = jcClassDecl.defs.prepend(makeGetterMethodDecl(jcVariableDecl));
//                    });
//                    super.visitClassDef(jcClassDecl);
//                }
//
//            });
//        });
//
//        return true;
//    }
//}
