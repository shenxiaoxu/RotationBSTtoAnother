/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rotation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import rotation.BST.Rotation.RotationType;

/**
 *
 * @author XiaoxuShen
 */
public class Rotation extends BST{
    private static ArrayList<Rotation> arrayList = new ArrayList<Rotation>();
    private static BST first;
    private static BST second;
    private static BST rotateNode;
    private static BST bst;
     private static BST parent;
     private static BST parentparent;
    
    public Rotation(Integer[] number){
        super(number);
    }
    
    public static ArrayList<Rotation> transform(BST first, BST second) {
        if(first == null || second == null){
            return null;
        }

        while(first.key != second.key){           
            if(leftNode(first, second.key)){
                //System.out.println("ZAG");
                parent = returnBST(first, second.key);
                //System.out.println(parent.print());
                parentparent = returnBST(first, parent.key);
                //System.out.println(parentparent.print());
                changeBST(parent, parentparent, RotationType.ZAG);
                //System.out.println(parentparent.print());
                if(parentparent.key == parent.key){
                    first = rotateNode;
                }else{
                    newBST(first, parentparent);
                }
                //System.out.println(first.print());
                
                Rotation node = new Rotation(second.key,RotationType.ZAG);
                arrayList.add(node);
            }else{
                //System.out.println("ZIG");
                parent = returnBST(first, second.key);
                //System.out.println(parent.print());
                parentparent = returnBST(first, parent.key);
                //System.out.println(parentparent.print());
                changeBST(parent, parentparent, RotationType.ZIG);
                if(parentparent.key == parent.key){
                    first = rotateNode;
                }else{
                    newBST(first, parentparent);
                }
                //System.out.println(first.print());
                Rotation node = new Rotation(second.key,RotationType.ZIG);
                arrayList.add(node);
            }
            
        }
        
        transform(first.left,second.left);
        
        transform(first.right,second.right);
        
        return arrayList;
        
    }
    
    /*
     * return parent of the node you want to rotate
     */
    public static BST returnBST(BST first, Integer data){
        if(first == null){
            return null;
        }
        if(first.left != null ){
            if(first.left.key == data ){
                bst = first;
                return bst;
            }
        }
        if(first.right != null ){
            if(first.right.key == data ){
                bst = first;
                return bst;
            }
        }
        returnBST(first.left, data);
        returnBST(first.right, data);
        return bst;
    }
    /*
     * to determine the rotate node is left branch or right branch
     */
    public static boolean leftNode(BST first, Integer data){
        
        if(first == null){return false;}
        if(first.left != null){
            if(first.left.key == data){
                return true;
            }
        }
        return leftNode(first.left, data)|| leftNode(first.right, data);   
    }
    /*
     * replace changed subtree with old subtree on first Binary search tree
     */
    public static void newBST(BST first, BST replaceSubTree){
        if(first != null){
            if(first.left != null){
                if(first.left.key == replaceSubTree.key){
                    first.left = replaceSubTree;
                }
            }
            if(first.right != null){
                if(first.right.key == replaceSubTree.key){
                    first.right = replaceSubTree;
                }
            }
        
        newBST(first.left, replaceSubTree);
        newBST(first.right, replaceSubTree); 
        }       
    }
    /*
     * perform zig or zag on the binary search tree
     */
    public static void changeBST(BST parent, BST parentparent, RotationType type){
        
        if(type == RotationType.ZAG){
            rotateNode = parent.left;
            if(parentparent.left != null){
                if(parentparent.left.key == parent.key){
                    parentparent.left = rotateNode;
                }
            } 
            
            if (parentparent.right != null) {
                if (parentparent.right.key == parent.key) {
                    parentparent.right = rotateNode;
                }
            }
            
            if(parent.left != null){
             parent.left = rotateNode.right;
            
              rotateNode.right = parent;
              
            }
            
        }else{
            rotateNode = parent.right;
            if (parentparent.left != null) {
                if (parentparent.left.key == parent.key) {
                    parentparent.left = rotateNode;
                }
            }

            if (parentparent.right != null) {
                if (parentparent.right.key == parent.key) {
                    parentparent.right = rotateNode;
                }
            }
            if (parent.right != null) {
                parent.right = rotateNode.left;

                rotateNode.left = parent;
            }
        }        
    }
    
    public static void main(String[] args){
        
        Integer[] array = {2,1,4,5,7,3,6,8,9};
        Integer[] array2 = {1,2,7,5,4,3,6,9,8};
        Integer[] array3 = {2,1};
        Integer[] array4 = {1,2};
        Integer[] array5 = {1,2,3,4,5};
        Integer[] array6 = {5,4,3,2,1};
        Integer[] test1 = null ;
        Integer[] test2 = null ;
        int jj = 0;
        int gg = 0;
        int size1=0;
        int size2;
        boolean aa = true;
        boolean bb = true;
        /*System.out.println("Please input first array, format is 1 2 3 4 5 -1");
        Scanner input = new Scanner(System.in);
        while(input.nextInt()!= -1){
            array5[ii] = input.nextInt();
            ii++;
        }*/
        
        /*System.out.println("Please input first array, format is 5 4 3 2 1");
         while(input.nextInt()!= -1){
            array6[jj] = input.nextInt();
            jj++;
        }*/
        
        //System.out.println(array5);
        //System.out.println(array6);
        
        for(String argument: args){
            System.out.println(argument);
            if(jj <= size1){
                if(Integer.valueOf(argument) != -1){
                if(jj == 0){
                    size1 = Integer.valueOf(argument);
                    test1 = new Integer[size1];
                    jj++;
                }else{
                    test1[jj] = Integer.valueOf(argument);
                    jj++;
                }
              }
              
            }
            
            /*while(Integer.valueOf(argument) != -1){
                if(gg == 0){
                    size2 = Integer.valueOf(argument);
                    test2 = new Integer[size2];
                }else{
                    test2[gg] = Integer.valueOf(argument);
                    gg++;
                }
            }*/
            //System.out.println(test[jj - 1]);
        }
        first = new BST(test1);
        second = new BST(test2);
        arrayList = transform(first, second);
        for(int ii = 0; ii < arrayList.size(); ii++){
            System.out.println(arrayList.get(ii).print());
        }
    }
}
