package com.blogspot.hartsock.functionalthinking;

/**
 * @author mansoor, hartsock
 * @since 2/7/12
 * <p/>
 * In an attempt to illustrate monadic type design in code very simply
 * I've extended the Pair type with a NonPair type.
 */
public class Pair<L,R> {
	@SuppressWarnings("serial")
	public static class HasNoLeft extends RuntimeException {};
	@SuppressWarnings("serial")
	public static class HasNoRight extends RuntimeException {};
	
	private final L left;
    private final R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }
    
    public boolean hasLeft() { return left != null; }
    public boolean hasRight() { return right != null; }
 
    /**
     * A non-valid container value NonPair is in the categorical
     * unit of Pair but still outside the range of valid Pair
     * combinations.
     * 
     * @author hartsock
     */
    public class NonPair extends Pair<L,R> {
    		public NonPair(L left, R right) {
    			super(left, right);
    		}
    		
    		public L getLeft() { throw new HasNoLeft(); }
    		public R getRight() { throw new HasNoRight(); }
    	
    		public boolean hasLeft() { return false; }
    		public boolean hasRight() { return false; }
    }
}
