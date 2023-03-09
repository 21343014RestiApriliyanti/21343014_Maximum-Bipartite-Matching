/*
    Nama        : Resti Apriliyanti
    NIM         : 21343014
    Algoritma   : Maximal Bipartite Matching
*/

class MPM {
	int M = 6;     // M = Pelamar Kerja
	int N = 6;     // N = Pekerjaan

	// Fungsi rekursif berbasis DFS (Depth First Search) yang mengembalikan true jika sumpul u cocok atau bernilai true
	boolean bpm(boolean graf[][], int u, boolean lihat[], int pencocokan[]) {
		for (int v = 0; v < N; v++) {			// Mencoba Pencocokan Job 1 per 1
			if (graf[u][v] && !lihat[v]) {		// Jika pelamar u tertarik dengan pekerjaan v dan v tidak dikunjungi
				lihat[v] = true;				// Tandai v sebagai telah dilihat

				// Jika pekerjaan 'v' tidak diberikan kepada pelamar 
				// atau pelamar yang sebelumnya ditugaskan untuk pekerjaan v (yang merupakan pencocokan[v]) yang tersedia
				if (pencocokan[v] < 0 || bpm(graf, pencocokan[v], lihat, pencocokan)) {
					pencocokan[v] = u;
					return true;
				}
			}
		}
		return false;
	}

	// Mengembalikan jumlah pencocokan maksimum dari M ke N
	int maxBPM(boolean graf[][]) {
		int pencocokan[] = new int[N];		// Array untuk melacak pelamar yang ditugaskan ke pekerjaan melalui pencocokan nomor N
		
		for(int i = 0; i < N; ++i)			// Inisialisasi pekerjaan tersedia
			pencocokan[i] = -1;

		int result = 0;						// Jumlah pekerjaan yang diberikan kepada pelamar
		for (int u = 0; u < M; u++) {
			boolean lihat[]=new boolean[N];	// Tandai semua pekerjaan sebagai tidak terlihat untuk pelamar berikutnya
			for(int i = 0; i < N; ++i)
				lihat[i] = false;

			if (bpm(graf, u, lihat, pencocokan))
				result++;
		}
		return result;
	}

public static void main (String[] args) throws java.lang.Exception {
	boolean graf[][] = new boolean[][]{
		{false, true, true, false, false, false},
		{true, false, false, true, false, false},
		{false, false, true, false, false, false},
		{false, false, true, true, false, false},
		{false, false, false, false, false, false},
		{false, false, false, false, false, true}
	};
	
	MPM m = new MPM();
	System.out.println( "Jumlah maksimum pelamar yang bisa mendapatkan pekerjaan adalah " + m.maxBPM(graf) + " orang");
	}
}
