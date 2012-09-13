/**
 * @author 高文龙
 */
package DLRP;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vertex {
	String name;
	int	ID;
	List<AdjacentVertex> adjacentVertex;
	List<Vertex> vertexList;
	List<Package> unPickUpPackageLis;
	
	/**
	 * 初始化物流网节点信息
	 * @param ID 节点ID
	 * @param name 节点名称
	 * @param adjacentVertex 邻接节点ID
	 * @param distance 邻接节点距离
	 */
	public Vertex(int ID, String name, int[] adjacentVertex, int[] distance) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		this.name = name;
		this.adjacentVertex = new ArrayList<Vertex.AdjacentVertex>();
		for (int i = 0; i < adjacentVertex.length; i++) {
			// AdjacentVertex是内嵌的辅助类
			this.adjacentVertex.add(new AdjacentVertex(adjacentVertex[i], distance[i]));
		}
		this.vertexList = null;
		this.unPickUpPackageLis = new ArrayList<Package>();
	}
	
	public int getVertexID() {
		return this.ID;
	}
	
	public String getVertexName() {
		return this.name;
	}
	
	public void setVertexList(List<Vertex> list) {
		this.vertexList = list;
	}
	
	public List<Vertex> getVertexList() {
		return this.vertexList;
	}
	
	/**
	 * show 向节点添加包裹
	 * @param pack 包裹
	 */
	public void addUnPickUpPackage(Package pack) {
		this.unPickUpPackageLis.add(pack);
	}
	
	/**
	 * show 删除掉节点包裹
	 * @param packIterator 包裹链表中的迭代器
	 */
	public void delUnPickUpPackage(Iterator<Package> packIterator) {
		packIterator.remove();
	}
	
	public List<Package> getUnPickUpPackage() {
		return this.unPickUpPackageLis;
	}
	
	public boolean hasUnPickPackage() {
		return !this.unPickUpPackageLis.isEmpty();
	}
	
	public int getPackageCount() {
		return this.unPickUpPackageLis.size();
	}
	
	/**
	 * show 获取邻接节点的包裹信息
	 * @return [i][0]: 节点ID [i][1]: 邻接节点包裹量 [i][2]: 邻接节点距离
	 */
	public double[][] askAdjacentPackages() {
		double[][] adjacentPackages = new double[this.adjacentVertex.size()][3];
		for (int i = 0; i < this.adjacentVertex.size(); i++) {
			adjacentPackages[i][0] = this.adjacentVertex.get(i).getVertexID();
			Vertex adjacent = this.vertexList.get(this.adjacentVertex.get(i).getVertexID());
			adjacentPackages[i][1] = adjacent.getPackageCount();
			adjacentPackages[i][2] = this.adjacentVertex.get(i).getDistance();
		}
		return adjacentPackages;
	}

	/**
	 * show 获取当前节点的包裹信息
	 * @return [i][0]: 节点ID  [i][1]: 本节点可以运载到邻接节点的包裹量    [i][2]: 邻接节点距离
	 */
	public double[][] askCurrentVertexPackages() {
		double[][] currentVertexPackage = new double [this.adjacentVertex.size()][3];
		for (int i = 0; i< this.adjacentVertex.size(); i++) {
			currentVertexPackage[i][0] = this.adjacentVertex.get(i).getVertexID();
			Vertex adjacent = this.vertexList.get(this.adjacentVertex.get(i).getVertexID());
			for (Package pack : this.unPickUpPackageLis) {
				if (pack.getShortestRouting().contains(adjacent)) {
					currentVertexPackage[i][1] ++;
				}
			}
			currentVertexPackage[i][2] = this.adjacentVertex.get(i).getDistance();
		}
		return currentVertexPackage;
	}
	
	public int getAdjacnetCount() {
		return this.adjacentVertex.size();
	}
	public List<Package> viewUnPickageList() {
		return this.unPickUpPackageLis;
	}
	
	public List<AdjacentVertex> getAdjacentVertex() {
		return this.adjacentVertex;
	}
	
	// 定义辅助类，邻近节点ID和 与邻近节点间距离
	class AdjacentVertex {
		private int vertexID;
		private int distance;
		
		public AdjacentVertex(int ID, int dist) {
			this.vertexID = ID;
			this.distance = dist;
		}
		
		public int getVertexID() {
			return this.vertexID;
		}
		
		public int getDistance() {
			return this.distance;
		}
	}
}
