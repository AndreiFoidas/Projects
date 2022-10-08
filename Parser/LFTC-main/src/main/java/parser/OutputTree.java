package parser;

import domain.Pair;

import java.util.*;

public class OutputTree {
    private TreeNode root;
    private final Grammar grammar;
    private int currentIndex = 1;
    private int indexInInput = 1;
    private int maxLevel = 0;
    private List<TreeNode> treeList;

    public OutputTree(Grammar grammar){
        this.grammar = grammar;
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode generateTreeFromSequence(List<Integer> inputSequence){
        int productionIndex = inputSequence.get(0);
        //System.out.println(inputSequence);
        Pair<List<String>,List<String>> productionString = this.grammar.getProductionOrder().get(productionIndex);
        this.root = new TreeNode(productionString.getFirst().get(0));
        this.root.setIndex(0);
        this.root.setLevel(0);

        this.root.setLeftChild(buildRecursive(1, this.root, productionString.getSecond(), inputSequence));
        return this.root;
    }

    public TreeNode buildRecursive(int level, TreeNode parent, List<String> currentContent, List<Integer> inputSequence){
        //System.out.println("start " + currentContent);
        if (currentContent.isEmpty() || this.indexInInput >= inputSequence.size() + 1) {
            return null;
        }

        String currentSymbol = currentContent.get(0);

        if (this.grammar.getTerminals().contains(currentSymbol)){
            TreeNode node = new TreeNode(currentSymbol);
            node.setIndex(this.currentIndex);
            this.currentIndex++;
            node.setLevel(level);
            node.setParent(parent);
            //System.out.println("terminal " + currentSymbol);
            List<String> newList = new ArrayList<>(currentContent);
            newList.remove(0);
            node.setRightSibling(buildRecursive(level, parent, newList, inputSequence));

            return node;
        }
        else if (this.grammar.getNonTerminals().contains(currentSymbol)){
            int productionIndex = inputSequence.get(this.indexInInput);
            Pair<List<String>,List<String>> productionString = this.grammar.getProductionOrder().get(productionIndex);
            TreeNode node = new TreeNode(currentSymbol); // sau productionString.getSecond()
            node.setIndex(this.currentIndex);
            node.setLevel(level);
            node.setParent(parent);
            int newLevel = level + 1;
            if (newLevel > this.maxLevel)
                this.maxLevel = newLevel;
            this.currentIndex++;
            this.indexInInput++;
            node.setLeftChild(buildRecursive(newLevel, node, productionString.getSecond(), inputSequence));
            //System.out.println("non terminal " + currentSymbol);
            List<String> newList = new ArrayList<>(currentContent);
            newList.remove(0);
            node.setRightSibling(buildRecursive(level, parent, newList, inputSequence));

            return node;

        } else {
            System.out.println("ERROR");
            return null;
        }
    }

    public void printTree(TreeNode node){
        this.treeList = new ArrayList<>();
        createList(node);

        for(int i=0;i<=this.maxLevel;i++){
            for(TreeNode n: this.treeList){
                if (n.getLevel() == i)
                    System.out.println(n);
            }
        }
    }

    public void createList(TreeNode node){
        if (node == null)
            return;

        while (node != null) {
            this.treeList.add(node);
            if (node.getLeftChild() != null) {
                createList(node.getLeftChild());
            }

            node = node.getRightSibling();
        }
    }
}
