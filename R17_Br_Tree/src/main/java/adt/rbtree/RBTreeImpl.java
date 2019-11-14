package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

import java.util.ArrayList;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

   public RBTreeImpl() {
      this.root = new RBNode<T>();
   }

   protected int blackHeight() {
      return blackHeight((RBNode<T>) this.root);
   }

   private int blackHeight(RBNode<T> node) {
      int height = 0;

      if (node != null && !node.isEmpty()) {

         if (node.getColour().equals(Colour.BLACK))
            height++;

         height += Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));

      }

      return height;
   }

   protected boolean verifyProperties() {
      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
            && verifyBlackHeight();

      return resp;
   }

   /**
    * The colour of each node of a RB tree is black or red. This is guaranteed
    * by the type Colour.
    */
   private boolean verifyNodesColour() {
      return true; // already implemented
   }

   /**
    * The colour of the root must be black.
    */
   private boolean verifyRootColour() {
      return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
      // implemented
   }

   /**
    * This is guaranteed by the constructor.
    */
   private boolean verifyNILNodeColour() {
      return true; // already implemented
   }

   /**
    * Verifies the property for all RED nodes: the children of a red node must
    * be BLACK.
    */
   private boolean verifyChildrenOfRedNodes() {
      return verifyChildrenOfRedNodes((RBNode<T>) this.root);
   }

   private boolean verifyChildrenOfRedNodes(RBNode<T> node) {

      if (node != null && !node.isEmpty()) {

         RBNode<T> leftNode = (RBNode<T>) node.getRight();
         RBNode<T> rightNode = (RBNode<T>) node.getLeft();

         if (node.getColour().equals(Colour.RED)) {

            if (!leftNode.getColour().equals(Colour.BLACK) || !rightNode.getColour().equals(Colour.BLACK))
               return false;

         }

         else {

            verifyChildrenOfRedNodes((RBNode<T>) node.getLeft());
            verifyChildrenOfRedNodes((RBNode<T>) node.getRight());

         }

      }

      return true;
   }

   /**
    * Verifies the black-height property from the root.
    */
   private boolean verifyBlackHeight() {
      return blackHeight((RBNode<T>) this.root.getLeft()) == blackHeight((RBNode<T>) this.root.getRight());
   }

   @Override
   public void insert(T element) {
      if (element != null)

         insert((RBNode<T>) this.root, element);

   }

   private void insert(RBNode<T> node, T element) {
      if (node.isEmpty()) {

         node.setData(element);
         node.setColour(Colour.RED);

         node.setLeft(new RBNode<>());
         node.getLeft().setParent(node);

         node.setRight(new RBNode<>());
         node.getRight().setParent(node);

         fixUpCase1(node);
      }

      else {

         if (node.getData().compareTo(element) > 0)

            insert((RBNode<T>) node.getLeft(), element);

         else
            insert((RBNode<T>) node.getRight(), element);

      }

   }

   @Override
   public RBNode<T>[] rbPreOrder() {

      ArrayList<RBNode<T>> array = new ArrayList<>();

      if (!this.isEmpty()) {

         this.rbPreOrder((RBNode<T>) this.root, array);

      }

      return array.toArray(new RBNode[this.size()]);

   }

   private void rbPreOrder(RBNode<T> node, ArrayList<RBNode<T>> array) {

      if (!node.isEmpty()) {

         array.add(node);

         this.rbPreOrder((RBNode<T>) node.getLeft(), array);
         this.rbPreOrder((RBNode<T>) node.getRight(), array);

      }

   }

   // FIXUP methods
   protected void fixUpCase1(RBNode<T> node) {
      if (node.getParent() == null)
         node.setColour(Colour.BLACK);

      else
         fixUpCase2(node);
   }

   protected void fixUpCase2(RBNode<T> node) {

      RBNode<T> parent = (RBNode<T>) node.getParent();

      if (parent != null && !parent.getColour().equals(Colour.BLACK))
         fixUpCase3(node);

   }

   protected void fixUpCase3(RBNode<T> node) {
      RBNode<T> avo = (RBNode<T>) node.getParent().getParent();
      RBNode<T> pai = (RBNode<T>) node.getParent();
      RBNode<T> tio;

      if (avo != null && !avo.isEmpty()) {

         if (avo.getRight().equals(pai))
            tio = (RBNode<T>) avo.getLeft();

         else
            tio = (RBNode<T>) avo.getRight();

         if (tio != null && tio.getColour().equals(Colour.RED)) {

            pai.setColour(Colour.BLACK);
            tio.setColour(Colour.BLACK);
            avo.setColour(Colour.RED);
            fixUpCase1(avo);

         }

         else
            fixUpCase4(node);

      }

   }

   protected void fixUpCase4(RBNode<T> node) {
      RBNode<T> pai = (RBNode<T>) node.getParent();
      RBNode<T> avo = (RBNode<T>) node.getParent().getParent();
      RBNode<T> next = node;

      if (pai.getRight().equals(node) && avo.getLeft().equals(pai)) {

         Util.leftRotation(pai);
         next = (RBNode<T>) node.getLeft();

      }

      else if (pai.getLeft().equals(node) && avo.getRight().equals(pai)) {

         Util.rightRotation(pai);
         next = (RBNode<T>) node.getRight();

      }

      fixUpCase5(next);
   }

   protected void fixUpCase5(RBNode<T> node) {
      RBNode<T> pai = (RBNode<T>) node.getParent();
      RBNode<T> avo = (RBNode<T>) node.getParent().getParent();

      pai.setColour(Colour.BLACK);
      avo.setColour(Colour.RED);

      RBNode<T> aux;

      if (pai.getLeft().equals(node))
         aux = (RBNode<T>) Util.rightRotation(avo);

      else
         aux = (RBNode<T>) Util.leftRotation(avo);

      if (aux.getParent() == null)
         this.root = aux;

   }
}
