package parser;

public class TreeNode {
    private int index;
    private String info;
    private TreeNode parent;
    private TreeNode rightSibling;
    private TreeNode leftChild;
    private int level;

    public TreeNode(String info){
        this.info = info;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getRightSibling() {
        return rightSibling;
    }

    public void setRightSibling(TreeNode rightSibling) {
        this.rightSibling = rightSibling;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "TreeNode: " +
                "index=" + index +
                ", info='" + info + '\'' +
                ", leftChild=" + (leftChild != null ? leftChild.getIndex() : -1) +
                ", rightSibling=" + (rightSibling != null ? rightSibling.getIndex() : -1) +
                ", parent=" + (parent != null ? parent.getIndex() : -1) +
                ", level=" + level;
    }
}
