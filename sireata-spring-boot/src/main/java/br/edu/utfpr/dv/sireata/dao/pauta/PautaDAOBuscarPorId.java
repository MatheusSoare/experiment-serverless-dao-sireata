package br.edu.utfpr.dv.sireata.dao.pauta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.utfpr.dv.sireata.dao.ConnectionDAO;
import br.edu.utfpr.dv.sireata.model.Pauta;

public class PautaDAOBuscarPorId {

    public Pauta buscarPorId(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = ConnectionDAO.getInstance().getConnection();
			stmt = conn.prepareStatement("SELECT * FROM pautas WHERE idPauta = ?");
		
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				return this.carregarObjeto(rs);
			}else{
				return null;
			}
		}finally{
			if((rs != null) && !rs.isClosed())
				rs.close();
			if((stmt != null) && !stmt.isClosed())
				stmt.close();
			if((conn != null) && !conn.isClosed())
				conn.close();
		}
    }
    
    private Pauta carregarObjeto(ResultSet rs) throws SQLException{
		Pauta pauta = new Pauta();
		
		pauta.setIdPauta(rs.getInt("idPauta"));
		pauta.getAta().setIdAta(rs.getInt("idAta"));
		pauta.setOrdem(rs.getInt("ordem"));
		pauta.setTitulo(rs.getString("titulo"));
		pauta.setDescricao(rs.getString("descricao"));
		
		return pauta;
	}    
}