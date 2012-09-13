/**
 * @author ������
 */
package DLRP;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Map {
	/**
	 * show �������ͼ��ʼ��
	 * <p>show ���ͼ�ڵ��ʼ�������ڵ���Ϣ�洢��vertexList��
	 * <br>show ������а����ĳ�ʼ��PackageConstruct()����������Ϣ���洢��packageList��
	 * <br>show ��ɳ����ĳ�ʼ��VehicleConstruct()����������Ϣֱ����Ϊ����ֵ
	 * @param packageList ������Ϣ����
	 * @param vehicleCount ��������
	 * @param vehicleCapity ��������
	 * @param packageCount ��������
	 * @return ��������
	 */
	private static PriorityQueue<Vehicle> MapConstruct(List<Package> packageList, int vehicleCount, int vehicleCapity, int packageCount) {
		// TODO Auto-generated method stub
		int[] A_vertex = {1, 2, 3, 14, 20};
		int[] A_distance = {40, 35, 42, 82, 63};
		Vertex A = new Vertex(0, "A�人", A_vertex, A_distance);
		
		int[] B_vertex = {0, 2, 7, 8, 21, 20};
		int[] B_distance = {40, 30, 63, 176, 120, 140};
		Vertex B = new Vertex(1, "B��ɳ", B_vertex, B_distance);
		
		int[] C_vertex = {0, 1, 3, 4, 5, 7, 23};
		int[] C_distance = {35, 30, 86, 110, 131, 94, 84};
		Vertex C = new Vertex(2, "C�ϲ�", C_vertex, C_distance);
		
		int[] D_vertex = {0, 2, 4, 14, 19};
		int[] D_distance = {42, 86, 10, 102, 108};
		Vertex D = new Vertex(3, "D�Ϸ�", D_vertex, D_distance);
		
		int[] E_vertex = {2, 3, 5, 6, 19, 22};
		int[] E_distance = {110, 10, 71, 78, 135, 32};
		Vertex E = new Vertex(4, "E�Ͼ�", E_vertex, E_distance);
		
		int[] F_vertex = {2, 4, 6, 23};
		int[] F_distance = {131, 71, 10, 89};
		Vertex F = new Vertex(5, "F����", F_vertex, F_distance);
		
		int[] G_vertex = {4, 5, 22};
		int[] G_distance = {78, 10 ,96};
		Vertex G = new Vertex(6, "G�Ϻ�", G_vertex, G_distance);
		
		int[] H_vertex = {1, 2, 8, 23};
		int[] H_distance = {63, 94, 153, 168};
		Vertex H = new Vertex(7, "H����", H_vertex, H_distance);
		
		int[] I_vertex = {1, 7, 9, 10};
		int[] I_distance = {63, 153, 65, 126};
		Vertex I = new Vertex(8, "I�Ͼ�", I_vertex, I_distance);
		
		int[] J_vertex = {8, 10, 12, 21};
		int[] J_distance = {65, 81, 55, 50};
		Vertex J = new Vertex(9, "J����", J_vertex, J_distance);
		
		int[] K_vertex = {8, 9, 11, 12};
		int[] K_distance = {126, 81, 131, 111};
		Vertex K = new Vertex(10, "K����", K_vertex, K_distance);
		
		int[] L_vertex = {10, 12, 13};
		int[] L_distance = {131, 42, 153};
		Vertex L = new Vertex(11, "L�ɶ�", L_vertex, L_distance);
		
		int[] M_vertex = {9, 10, 11, 13, 20, 21};
		int[] M_distance = {55, 111, 42, 159, 170, 68};
		Vertex M = new Vertex(12, "M����", M_vertex, M_distance);
		
		int[] N_vertex = {11, 12, 14, 15, 20};
		int[] N_distance = {153, 159, 140, 126, 69};
		Vertex N = new Vertex(13, "N����", N_vertex, N_distance);
		
		int[] O_vertex = {0, 3, 13, 15, 16, 19, 20};
		int[] O_distance = {82, 102, 140, 72, 51, 111, 58};
		Vertex O = new Vertex(14, "O֣��", O_vertex, O_distance);
		
		int[] P_vertex = {13, 14, 16, 18};
		int[] P_distance = {126, 72, 28, 65};
		Vertex P = new Vertex(15, "P̫ԭ", P_vertex, P_distance);
		
		int[] Q_vertex = {14, 15, 17, 18, 19};
		int[] Q_distance = {51, 28, 33, 30, 59};
		Vertex Q = new Vertex(16, "Qʯ��ׯ", Q_vertex, Q_distance);
		
		int[] R_vertex = {16, 18, 19};
		int[] R_distance = {33, 10, 40};
		Vertex R = new Vertex(17, "R���", R_vertex, R_distance);
		
		int[] S_vertex = {15, 16, 17};
		int[] S_distance = {65, 30, 10};
		Vertex S = new Vertex(18, "S����", S_vertex, S_distance);
		
		int[] T_vertex = {3, 4, 14, 16, 17, 22};
		int[] T_distance = {108, 135, 111, 59, 40, 58};
		Vertex T = new Vertex(19, "T����", T_vertex, T_distance);
	
		int[] U_vertex = {0, 1, 12, 13, 14, 21};
		int[] U_distance = {63, 140, 170, 69, 58, 135};
		Vertex U = new Vertex(20, "U�差", U_vertex, U_distance);
		
		int[] V_vertex = {1, 9, 12, 20};
		int[] V_distance = {120, 50, 68, 135};
		Vertex V = new Vertex(21, "V����", V_vertex, V_distance);
	
		int[] W_vertex = {4, 6, 19};
		int[] W_distance = {32, 96, 58};
		Vertex W = new Vertex(22, "W���Ƹ�", W_vertex, W_distance);
	
		int[] X_vertex = {2, 5, 7};
		int[] X_distance = {84, 89, 168};
		Vertex X = new Vertex(23, "X����", X_vertex, X_distance);
		
		List<Vertex> vertexList = new ArrayList<Vertex>();
		vertexList.add(A);
		vertexList.add(B);
		vertexList.add(C);
		vertexList.add(D);
		vertexList.add(E);
		vertexList.add(F);
		vertexList.add(G);
		vertexList.add(H);
		vertexList.add(I);
		vertexList.add(J);
		vertexList.add(K);
		vertexList.add(L);
		vertexList.add(M);
		vertexList.add(N);
		vertexList.add(O);
		vertexList.add(P);
		vertexList.add(Q);
		vertexList.add(R);
		vertexList.add(S);
		vertexList.add(T);
		vertexList.add(U);
		vertexList.add(V);
		vertexList.add(W);
		vertexList.add(X);
		
		for (Vertex vertex : vertexList) {
			vertex.setVertexList(vertexList);
		}
		
		// ���ư�����Ϣ��ʼ�����������ڵ�������������
		PackageConstruct(packageCount, vertexList, packageList);
		
		for (Package pack : packageList) {
			A.addUnPickUpPackage(pack);
		}
		
		// ������Ϣ��ʼ��������������������ʼ�㣩
		return VehicleConstruct(vehicleCount, vehicleCapity, A);
	}
	
	/**
	 * show ������Ϣ��ʼ��
	 * <p>show ʹ��������ɳ���ʼ����������ڵ�ID��������Ϊ������Ŀ�Ľڵ�
	 * @param packageCount ���ɵİ�������
	 * @param vertexList �������Ľڵ���Ϣ
	 * @param packageList ���������ڴ˺����г�ʼ��������
	 */
	public static void PackageConstruct(int packageCount, List<Vertex> vertexList, List<Package> packageList) {
		int vertexCount = vertexList.size();
		for (int i = 0; i < packageCount; i++) {
			// ������ų����⣬���ɴ�0~22�������
			int random = (int) (Math.random()*(vertexCount - 1));
			Package pack = new Package(i, i+vertexList.get(random + 1).getVertexName(), 1, 
					vertexList.get(0), vertexList.get(random + 1));
			packageList.add(pack);
		}
	}
	
	/**
	 * show ������Ϣ��ʼ��
	 * <p>show ���ɳ������ȼ����У�ͨ������������ʱ��������������ȼ�
	 * @param vehicleCount ��������
	 * @param vehicleCapacity ��������
	 * @param origin ������ʼ��
	 * @return �������ȼ�����
	 */
	public static PriorityQueue<Vehicle> VehicleConstruct(int vehicleCount, int vehicleCapacity, Vertex origin) {
		PriorityQueue<Vehicle> vehicleList = new PriorityQueue<Vehicle>(vehicleCount, 
				new Comparator<Vehicle>() {
					public int compare(Vehicle v1, Vehicle v2) {
						return v1.getRunningDistance().compareTo(v2.getRunningDistance());
					}
				});
		
		for (int i = 0; i < vehicleCount; i++)	{
			Vehicle vehicle = new Vehicle(i, vehicleCapacity, origin);
			vehicleList.add(vehicle);
		}
		
		return vehicleList;
	}

	/**
	 * show DLRP�㷨��������
	 * <p>show 1:ͨ�����������ȼ���ȷ����ǰ�����ĸ��ڵ㣬�����а����������͵�����ת5.
	 * <br>show 2:�������ڽڵ����Ȩֵ��(�ڽӽڵ�����Ͱ���*0.1+��ǰ�ڵ�����Ͱ���*0.9)/(·������/�����ڽӽڵ��·������֮��),
	 * <br>show ��������ǰ�������Ȩֵ���Ľڵ㡣��ȨֵΪ0����ת3��������ת4
	 * <br>show 3:��δ���͵�λ�İ�����ǰ�ڵ���˳����ĵ�ǰ����������·����ѡȡ������̵�·��������ǰ����һ�ڵ㣬��ת4
	 * <br>show 4:����������һ�ڵ㣬���³����������Ͱ����ĵ�ǰ�ڵ㣬�����³��������о���
	 * <br>show 5:���а����������͵�������ֹͣ���㷨����
	 * @param argv
	 */
	public static void main(String[] argv) {
		int vehicleCount = 4;
		int vehicleCapity = 25;
		int packageCount = 250;
		
		createFileDirectory(vehicleCount, vehicleCapity, packageCount);
		
		for (int i = 0; i < 30; i++){
			RunningDLRP(i, vehicleCount, vehicleCapity, packageCount);
		}
	}

	private static void RunningDLRP(int ID, int vehicleCount, int vehicleCapity, int packageCount) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("data//" + 
					"vCount" + vehicleCount + "vCapity" + vehicleCapity + "pCount" + packageCount + "//" + ID + ".txt");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<Package> packageList = new ArrayList<Package>();
		PriorityQueue<Vehicle> vehicleList = MapConstruct(packageList, vehicleCount, vehicleCapity, packageCount);
		try {
			for (Package pack : packageList) {
				fileWriter.write(pack.getPackageName() + ":" + pack.getShortestDistance() + "  ");
			}
			fileWriter.write("\nvehicleCout:" + vehicleCount +"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (packageList.isEmpty() != true) {
			Vehicle vehicle = vehicleList.poll();
			Vertex currentVertex = vehicle.getCurrentVertex();
			int adjacentCount = currentVertex.getAdjacnetCount();
			double[][] adjacentPackages = currentVertex.askAdjacentPackages();
			double[][] currentVertexPackage = currentVertex.askCurrentVertexPackages();
			
			int maxWeightID = 0;
			double maxWeight = 0;
			
			double adjacentLength = 0;
			for (int i = 0; i < adjacentCount; i++) {
				adjacentLength += adjacentPackages[i][2];
			}
			
			for (int i = 0; i < adjacentCount; i++) {
				double weight = (adjacentPackages[i][1] * 0.2 + currentVertexPackage[i][1] * 0.8) / (adjacentPackages[i][2] / adjacentLength);
				// Ȩֵ��󣬲��Ҳ��ǳ�����ʱ����һ�ڵ�
				if (weight > maxWeight && !vehicle.isLastStep(currentVertex.getVertexList().get((int)currentVertexPackage[i][0]))) {
					maxWeightID = (int)currentVertexPackage[i][0];
					maxWeight = weight;
				}
			}
			
			// ���ع��ߵ�ǰ�ڵ㼰���ڽӽڵ㶼δ�д����Ͱ���������ǰ������İ��������ͽڵ�
			if (maxWeight == 0) {
				int[] shortestID = {9999, 0};
				for (Package pack : packageList) {
					// ��Ϊ�������ܷ�����ʱ�Ľڵ㣬�����п��ܳ��ֳ�����ǰ�ڵ��а������޷�ʹ�����·��
					if (vehicle.getCurrentVertex().equals(pack.getCurrentVertex())) {
						continue;
					}
					int[] tmp = vehicle.findShortestRoutingFromCurrent(pack.getCurrentVertex());
					if (tmp[0] < shortestID[0]) {
						shortestID[0] = tmp[0];
						shortestID[1] = tmp[1];
					}
				}
				
				try {
					double dist = 0;
					for (int i = 0; i < adjacentCount; i++) {
						if (adjacentPackages[i][0] == shortestID[1]) {
							dist = adjacentPackages[i][2];
							break;
						}
					}
					fileWriter.write("vID:" + vehicle.getVehicleID() + " vLoading:" + vehicle.getLoadingSize() + " nextDist:" + dist + "\n");
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				vehicle.setNextStep(currentVertex.getVertexList().get(shortestID[1]));
				vehicle.running();
				vehicle.arriveVertex();	
				
				vehicleList.add(vehicle);
				continue;
			}
			
			
			////����
//			System.out.println("VehicleID: " + vehicle.getVehicleID());
//			System.out.println("currentVertexID: " + currentVertex.getVertexID() + 
//					"   name: " + currentVertex.getVertexName());
//			System.out.println("PickUpPackages: ");
			////
			List<Package> currentPackageList = currentVertex.getUnPickUpPackage();
			Vertex nextStep = currentVertex.getVertexList().get(maxWeightID);
			
			Iterator<Package> iter = currentPackageList.iterator();
			while (iter.hasNext()) {
				Package pack = iter.next();
				if (pack.getDestion().equals(nextStep)) {
					if (vehicle.pickUpPackage(pack) == false) {
						break;
					}
					currentVertex.delUnPickUpPackage(iter);
					packageList.remove(pack);
					//// ����
//					System.out.println("send success!  PackageID: " + pack.getPackageID() + " " + pack.name + ";  ");
					////
					continue;
				}
				if (pack.getShortestRouting().contains(nextStep)) {
					if (vehicle.pickUpPackage(pack) == false) {
						break;
					}
					currentVertex.delUnPickUpPackage(iter);
					pack.delVertexOfShortesting(nextStep);
					//// ����
//					System.out.print(pack.getPackageID() + " " + pack.name + ";  ");
					////
				}
			}
			
			try {
				double dist = 0;
				for (int i = 0; i < adjacentCount; i++) {
					if (adjacentPackages[i][0] == nextStep.getVertexID()) {
						dist = adjacentPackages[i][2];
						break;
					}
				}
				fileWriter.write("vID:" + vehicle.getVehicleID() + " vLoading:" + vehicle.getLoadingSize() + " nextDist:" + dist + "\n");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			vehicle.setNextStep(nextStep);
			vehicle.running();
			vehicle.arriveVertex();
			
			////����
//			System.out.println(" /nDestionID: " + nextStep.getVertexID() + 
//					"  name: " + nextStep.getVertexName() + 
//					"  vehicle running distance: " + vehicle.getRunningDistance() + "\n\n");
			////
			vehicleList.add(vehicle);
		}
		try {
//			fileWriter.write("\n");
			for (Vehicle vehicle : vehicleList) {
				fileWriter.write("vID:" + vehicle.getVehicleID() + "vRunning:" + vehicle.getRunningDistance() + "\n");
			}	
			fileWriter.write("\nAll Package Send Success!");
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("All Vehilce Stop!");
	}
	
	private static boolean createFileDirectory(int vehicleCount, int vehicleCapity, int packageCount) {
		try {
			File fileDir = new File("data//" + "vCount" + vehicleCount + "vCapity" + vehicleCapity + "pCount" + packageCount);
			if (!fileDir.isDirectory() && !fileDir.exists()) {
				boolean create = fileDir.mkdirs();
				if (create) {
					System.out.println("create success " + vehicleCount + " " + vehicleCapity + " " + packageCount);
				}else {
					System.out.println("create failed " + vehicleCount + " " + vehicleCapity + " " + packageCount);
					return false;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
}

