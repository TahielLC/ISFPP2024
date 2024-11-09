package gui.datos.datos;

import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import aplicacion.Coordinador;
import modelo.Equipo;
import modelo.TipoEquipo;
import modelo.TipoPuerto;
import modelo.Ubicacion;

public class ManipularEquipo {
			
		private Manipular manipular;
		public ManipularEquipo() {
			
		}
		/** 
		 * El metodo <b>panelAgregar</b> se encarga de
		 * agregar los nuevos equipos a la red
		 * @param Recibe el panel del campo
		 * @return un Equipo
		 */
		public void panelAgregar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar, 
				Coordinador coordinador) {
			// Limpia el panel antes de agregar nuevos componentes
			campo.removeAll();
		       
			modificar.setVisible(false);
		        
			borrar.setVisible(false);
		      
			// campo 1 (codigo)
			JLabel codigoL = new JLabel();
			codigoL.setText("Codigo:");
			codigoL.setBounds(50, 0, 100, 100);
			campo.add(codigoL);
			
			JTextField codigoT = new JTextField(15);
			codigoT.setBounds(140, 40, 120, 20);
			campo.add(codigoT);
			
			// campo 2 (descripcion)
			JLabel descripcionL = new JLabel();
			descripcionL.setText("Descripcion:");
			descripcionL.setBounds(50, 40, 100, 100);
			campo.add(descripcionL);
				
			JTextField descripcionT = new JTextField(15);
			descripcionT.setBounds(140, 80, 120, 20);
			campo.add(descripcionT);
				
			// campo 3 (marca)
			JLabel marcaL = new JLabel();
			marcaL.setText("Marca:");
			marcaL.setBounds(50, 80, 100, 100);
			campo.add(marcaL);
				
			JTextField marcaT = new JTextField(15);
			marcaT.setBounds(140, 120, 120, 20);
			campo.add(marcaT);
			
			// campo 4 (modelo)
			JLabel modeloL = new JLabel();
			modeloL.setText("Modelo:");
			modeloL.setBounds(50, 120, 100, 100);
			campo.add(modeloL);
			
			JTextField modeloT = new JTextField(15);
			modeloT.setBounds(140, 160, 120, 20);
			campo.add(modeloT);
			
			// campo 5 (direccion IP)
			JLabel direccionipL = new JLabel();
			direccionipL.setText("Direccion IP (Separados por coma):");
			direccionipL.setBounds(50, 160, 210, 100);
			campo.add(direccionipL);
				
			JTextField direccionipT = new JTextField();
			direccionipT.setBounds(50, 240, 350, 20);
			campo.add(direccionipT);
			
			// campo 6 (Ubicacion)
			JLabel ubicacionL = new JLabel();
			ubicacionL.setText("Ubicacion (Cada ubicacion como'codigo,descripcion'):");
			ubicacionL.setBounds(50, 220, 310, 100);
			campo.add(ubicacionL);
				
			JTextField ubicacionT = new JTextField();
			ubicacionT.setBounds(50, 300, 350, 20);
			campo.add(ubicacionT);
				
			// campo 7 (TipoEquipo)
			JLabel tipoEquipoL = new JLabel();
			tipoEquipoL.setText("Tipo de Equipo (Cada equipo como 'codigo,descripcion'):");
			tipoEquipoL.setBounds(50, 300, 320, 100);
			campo.add(tipoEquipoL);
				
			JTextField tipoEquipoT = new JTextField(15);
			tipoEquipoT.setBounds(50, 360, 350, 20);
			campo.add(tipoEquipoT);
				
			// campo 7 (Puertos)
			JLabel puertoL = new JLabel();
			puertoL.setText("Tipo Puerto (Cada puerto como 'codigo,descripcion,velocidad:cantidad'):");
			puertoL.setBounds(50, 360, 410, 100);
			campo.add(puertoL);
				
			JTextField puertoT = new JTextField(15);
			puertoT.setBounds(50, 440, 350, 20);
			campo.add(puertoT);
				
						
			panelInferior.add(cargar);
			cargar.setVisible(true);
			
			cargar.addActionListener(e -> {
				try {
					// Recuperar los datos y crear el objeto equipo
					Equipo equipo = cargarEquipo(codigoT, descripcionT, marcaT, modeloT, direccionipT, 
							ubicacionT, tipoEquipoT, puertoT);
	
					// Verificar si el equipo cumple con las validaciones
					if (validarAgregar(equipo)) {
						coordinador.insertarEquipo(equipo);
						JOptionPane.showMessageDialog(null, "Equipo agregado exitosamente.");
					} else {
						JOptionPane.showMessageDialog(null, "Error: Verifica los datos del equipo.", "Error de validación", 
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error en el formato de entrada: " + ex.getMessage(), "Error de Formato", 
							JOptionPane.ERROR_MESSAGE);
				}
			});
				
			campo.revalidate();
			campo.repaint();
					
		}
		public Equipo cargarEquipo(JTextField codigoT,JTextField descripcionT, JTextField marcaT,JTextField modeloT,
				JTextField direccionipT, JTextField ubicacionT, JTextField tipoEquipoT, JTextField puertosT) {
			String codigo = codigoT.getText();
			String descripcion = descripcionT.getText();
			String marca = marcaT.getText();
			String modelo = modeloT.getText();
				
			String[] ubicacionDatos = ubicacionT.getText().split(",");
			Ubicacion ubicacion = new Ubicacion(ubicacionDatos[0], ubicacionDatos[1]);
				
			String[] tipoEquipoDatos = tipoEquipoT.getText().split(",");
			TipoEquipo tipoEquipo = new TipoEquipo(tipoEquipoDatos[0], tipoEquipoDatos[1]);
				
			// instanciamos un objeto de tipo Equipo
			Equipo equipo = new Equipo(codigo, descripcion, marca, modelo, ubicacion, tipoEquipo, true);;
				
			String[] direccionIPdatos = direccionipT.getText().split(",");
			for(String dir: direccionIPdatos) {
				equipo.agregarIp(dir.trim());
			}
				
			for (String puertoData : puertosT.getText().split(",")) {
				String[] parts = puertoData.split(":");
				String[] tipoPuertoData = parts[0].split(",");
			        
				// Crear el TipoPuerto
				TipoPuerto tipoPuerto = new TipoPuerto(tipoPuertoData[0], tipoPuertoData[1], Integer.parseInt(tipoPuertoData[2]));
			        
				// Convertir la cantidad y agregar puerto
				int cantidad = Integer.parseInt(parts[1].trim());
				equipo.agregarPuerto(tipoPuerto, cantidad);
			}
	
			return equipo;
		}
			
		private boolean validarAgregar(Equipo equipo) {
			String[] validarCodigo = new String[]{"AP","CAM","COM","IMP","IJ","NAS","NVR","RT","SW"};
			boolean valido = false;
			if(equipo.getCodigo() == null || equipo.getCodigo().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El codigo: " + equipo.getCodigo() + " esta vacio o "
						+ "es nulo. ","Error: ", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			for(String prefijo : validarCodigo) {
				if(equipo.getCodigo().startsWith(prefijo) && equipo.getCodigo().equals(equipo.getCodigo().toUpperCase())) {
					valido = true;
				}
					
				if(!valido) {
					JOptionPane.showMessageDialog(null, "El codigo: " + equipo.getCodigo() + " no tiene el formato correcto o no"
							+ "representa el codigo de un tipo de equipo.","Error: ", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				
			}
				
			if(equipo.getDescripcion() != null || !equipo.getDescripcion().isEmpty()) {
				JOptionPane.showMessageDialog(null,"La descripcion no puede estar vacia.","Error: ",JOptionPane.ERROR_MESSAGE);
				return false;
			}
				
			return false;
		}
		
		
		public void panelModificar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar, 
				Coordinador coordinador) {
			// Limpia el panel antes de agregar nuevos componentes
	        campo.removeAll();
	        
	        cargar.setVisible(false);
	        
	        borrar.setVisible(false);
	        
	        
			// campo 1 (codigo)
			JLabel codigoL = new JLabel();
			codigoL.setText("Codigo:");
			codigoL.setBounds(50, 0, 100, 100);
			campo.add(codigoL);
			
			JComboBox<String> codigoT = new JComboBox<>(coordinador.getManipular().obtenerListaCodigo());
			codigoT.setBounds(140, 40, 120, 20);
			codigoT.setEditable(true);
			campo.add(codigoT);
			
			// campo 2 (descripcion)
			JLabel descripcionL = new JLabel();
			descripcionL.setText("Descripcion:");
			descripcionL.setBounds(50, 40, 100, 100);
			campo.add(descripcionL);
					
			JTextField descripcionT = new JTextField(15);
			descripcionT.setBounds(140, 80, 120, 20);
			campo.add(descripcionT);
					
			// campo 3 (marca)
			JLabel marcaL = new JLabel();
			marcaL.setText("Marca:");
			marcaL.setBounds(50, 80, 100, 100);
			campo.add(marcaL);
					
			JTextField marcaT = new JTextField(15);
			marcaT.setBounds(140, 120, 120, 20);
			campo.add(marcaT);
					
			// campo 4 (modelo)
			JLabel modeloL = new JLabel();
			modeloL.setText("Modelo:");
			modeloL.setBounds(50, 120, 100, 100);
			campo.add(modeloL);
					
			JTextField modeloT = new JTextField(15);
			modeloT.setBounds(140, 160, 120, 20);
			campo.add(modeloT);
					
			// campo 5 (direccion IP)
			JLabel direccionipL = new JLabel();
			direccionipL.setText("Direccion IP (Separados por coma):");
			direccionipL.setBounds(50, 160, 210, 100);
			campo.add(direccionipL);
					
			JComboBox<String> direccionipT = new JComboBox<>(); // Recibe una lista de direciones IPs
			direccionipT.setBounds(50, 240, 350, 20);
			campo.add(direccionipT);
			// campo 6 (Ubicacion) aqui tendremos un campo para el codigo y otro para la descripcion
					
			JLabel ubicacionL = new JLabel();
			ubicacionL.setText("Ubicacion (Cada ubicacion como'codigo,descripcion'):");
			ubicacionL.setBounds(50, 220, 310, 100);
			campo.add(ubicacionL);
					
			JTextField ubicacionT = new JTextField();
			ubicacionT.setBounds(50, 300, 350, 20);
			campo.add(ubicacionT);
				
			// campo 7 (tipoEquipo)
			JLabel tipoEquipoL = new JLabel();
			tipoEquipoL.setText("Tipo de Equipo (Cada equipo como 'codigo,descripcion'):");
			tipoEquipoL.setBounds(50, 300, 320, 100);
			campo.add(tipoEquipoL);
					
			JTextField tipoEquipoT = new JTextField(15);
			tipoEquipoT.setBounds(50, 360, 350, 20);
			campo.add(tipoEquipoT);
					
			// campo 8 (Puertos)
			JLabel puertoL = new JLabel();
			puertoL.setText("Tipo Puerto (Cada puerto como 'codigo,descripcion,velocidad:cantidad'):");
			puertoL.setBounds(50, 360, 410, 100);
			campo.add(puertoL);
					
			JComboBox<String> puertoT = new JComboBox<>();
			puertoT.setBounds(50, 440, 350, 20);
			campo.add(puertoT);
			
			
	        
			codigoT.addActionListener(e -> {
				String codigoSeleccionado = (String) codigoT.getSelectedItem();
		        Equipo equipo = coordinador.getRed().buscarEquipoPorCodigo(codigoSeleccionado);
		        // Verificar si se encontró el equipo
		        if (equipo != null) {
		        	descripcionT.setText("");
		        	
		        	descripcionT.setText(equipo.getDescripcion());
		        	marcaT.setText("");
		        	marcaT.setText(equipo.getMarca());
		            
		        	modeloT.setText("");
		        	modeloT.setText(equipo.getModelo());
		            
		            direccionipT.removeAllItems();
		            for (String ip : equipo.getIPs()) {
		                direccionipT.addItem(ip);
		            }
		            
		            ubicacionT.setText("");
		            String cubicacion = equipo.getUbicacion().getCodigo();
		            String dubicacion = equipo.getUbicacion().getDescripcion();
		            ubicacionT.setText(cubicacion+","+dubicacion);
		            
		            tipoEquipoT.setText("");
		            String cTipoEquipo = equipo.getTipoEquipo().getCodigo();
		            String dTipoEquipo = equipo.getTipoEquipo().getDescripcion();
		            tipoEquipoT.setText(cTipoEquipo+","+dTipoEquipo);
		            
		            puertoT.removeAllItems();
		            List<String> puertos = equipo.getPuertos();
		            for (String puerto : puertos) {
		                puertoT.addItem(puerto);
		            }
		               
		        } else {
		            JOptionPane.showMessageDialog(null, "El equipo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    });
			
			panelInferior.add(modificar);
			modificar.setVisible(true);
			
			modificar.addActionListener(e ->{
				try {
					Equipo equip;
					String codigo = (String) codigoT.getSelectedItem();
		            String descripcion = descripcionT.getText();
		            String marca = marcaT.getText();
		            String modelo = modeloT.getText();
		            // ver como guardar los cambios
		            List<String> direccionesIP = Arrays.asList(direccionipT.getSelectedItem().toString().split(","));
		            
		            
		            String[] ubi = ubicacionT.getText().split(",");
		            Ubicacion ubicacion = new Ubicacion(ubi[0],ubi[1]); 
		           
		            String[] tipoequipo = tipoEquipoT.getText().split(",");
		            TipoEquipo tipoEquipo = new TipoEquipo(tipoequipo[0],tipoequipo[1]);
		            
		            String textoPuertos = (String) puertoT.getSelectedItem();
		            String[] puertoDatos = textoPuertos.split(":");

		    	    for (String dato : puertoDatos) {
		    	        String[] detalles = dato.split(",");
		    	        
		    	        // Verifica que haya los tres elementos necesarios para TipoPuerto
		    	        if (detalles.length == 4) {
		    	            String cod = detalles[0];
		    	            String des = detalles[1];
		    	            int velocidad = Integer.parseInt(detalles[2]);
		    	            int cantidad = Integer.parseInt(detalles[3]);
		    	            // Crear el TipoPuerto
		    	            TipoPuerto tipoPuerto = new TipoPuerto(cod, des, velocidad);
		    	            // ver como guardar los cambios de los Puertos
		    	        }
		    	    }
		    	    equip = new Equipo(codigo, descripcion, marca, modelo, ubicacion, tipoEquipo, true);
		    	    equip.setDireccionIP(direccionesIP);
					if(validarModificar(equip)) {
						coordinador.modificarEquipo(equip); // recibe un equipo
					}
				} catch(Exception ex) {
			        JOptionPane.showMessageDialog(null, "Error en el formato de entrada: " + ex.getMessage(), "Error de Formato", 
			        		JOptionPane.ERROR_MESSAGE);
					
				}
			});
			direccionipT.removeAllItems(); 
	        puertoT.removeAllItems(); 
	        
			campo.revalidate();
		    campo.repaint();
		}
		
		// falta implementar la logica de para validar
		private boolean validarModificar(Equipo equipo) {
			return false;
		}
		
		public void panelBorrar(JPanel campo, JPanel panelInferior, JButton cargar, JButton modificar, JButton borrar, 
				Coordinador coordinador) {
			// Limpia el panel antes de agregar nuevos componentes
	        campo.removeAll();
	        
	        cargar.setVisible(false);
	        
	        modificar.setVisible(false);
	        
			// campo 1 (codigo)
			JLabel codigoL = new JLabel();
			codigoL.setText("Codigo:");
			codigoL.setBounds(50, 0, 100, 100);
			campo.add(codigoL);
			
			JComboBox<String> codigoT = new JComboBox<>(coordinador.getManipular().obtenerListaCodigo());
			codigoT.setBounds(140, 40, 120, 20);
			campo.add(codigoT);
			
			// campo 2 (descripcion)
			JLabel descripcionL = new JLabel();
			descripcionL.setText("Descripcion:");
			descripcionL.setBounds(50, 40, 100, 100);
			campo.add(descripcionL);
					
			JTextField descripcionT = new JTextField(15);
			descripcionT.setBounds(140, 80, 120, 20);
			campo.add(descripcionT);
					
			// campo 3 (marca)
			JLabel marcaL = new JLabel();
			marcaL.setText("Marca:");
			marcaL.setBounds(50, 80, 100, 100);
			campo.add(marcaL);
					
			JTextField marcaT = new JTextField(15);
			marcaT.setBounds(140, 120, 120, 20);
			campo.add(marcaT);
					
			// campo 4 (modelo)
			JLabel modeloL = new JLabel();
			modeloL.setText("Modelo:");
			modeloL.setBounds(50, 120, 100, 100);
			campo.add(modeloL);
					
			JTextField modeloT = new JTextField(15);
			modeloT.setBounds(140, 160, 120, 20);
			campo.add(modeloT);
					
			// campo 5 (direccion IP)
			JLabel direccionipL = new JLabel();
			direccionipL.setText("Direccion IP (Separados por coma):");
			direccionipL.setBounds(50, 160, 210, 100);
			campo.add(direccionipL);
					
			JComboBox<String> direccionipT = new JComboBox<>(); // Recibe una lista de direciones IPs
			direccionipT.setBounds(50, 240, 350, 20);
			campo.add(direccionipT);
			// campo 6 (Ubicacion) aqui tendremos un campo para el codigo y otro para la descripcion
					
			JLabel ubicacionL = new JLabel();
			ubicacionL.setText("Ubicacion (Cada ubicacion como'codigo,descripcion'):");
			ubicacionL.setBounds(50, 220, 310, 100);
			campo.add(ubicacionL);
					
			JTextField ubicacionT = new JTextField();
			ubicacionT.setBounds(50, 300, 350, 20);
			campo.add(ubicacionT);
				
			// campo 7 (tipoEquipo)
			JLabel tipoEquipoL = new JLabel();
			tipoEquipoL.setText("Tipo de Equipo (Cada equipo como 'codigo,descripcion'):");
			tipoEquipoL.setBounds(50, 300, 320, 100);
			campo.add(tipoEquipoL);
					
			JTextField tipoEquipoT = new JTextField(15);
			tipoEquipoT.setBounds(50, 360, 350, 20);
			campo.add(tipoEquipoT);
					
			// campo 8 (Puertos)
			JLabel puertoL = new JLabel();
			puertoL.setText("Tipo Puerto (Cada puerto como 'codigo,descripcion,velocidad:cantidad'):");
			puertoL.setBounds(50, 360, 410, 100);
			campo.add(puertoL);
					
			JComboBox<String> puertoT = new JComboBox<>();
			puertoT.setBounds(50, 440, 350, 20);
			campo.add(puertoT);
			
			
			
			codigoT.addActionListener(e -> {
				String codigoSeleccionado = (String) codigoT.getSelectedItem();
		        Equipo equipo = coordinador.getRed().buscarEquipoPorCodigo(codigoSeleccionado);
		        // Verificar si se encontró el equipo
		        if (equipo != null) {
		        	descripcionT.setText("");
		        	descripcionT.setText(equipo.getDescripcion());
		        	
		        	marcaT.setText("");
		        	marcaT.setText(equipo.getMarca());
		            
		        	modeloT.setText("");
		        	modeloT.setText(equipo.getModelo());
		            
		            direccionipT.removeAllItems();
		            for (String ip : equipo.getIPs()) {
		                direccionipT.addItem(ip);
		            }
		            
		            ubicacionT.setText("");
		            String cubicacion = equipo.getUbicacion().getCodigo();
		            String dubicacion = equipo.getUbicacion().getDescripcion();
		            ubicacionT.setText(cubicacion+","+dubicacion);
		            
		            tipoEquipoT.setText("");
		            String cTipoEquipo = equipo.getTipoEquipo().getCodigo();
		            String dTipoEquipo = equipo.getTipoEquipo().getDescripcion();
		            tipoEquipoT.setText(cTipoEquipo+","+dTipoEquipo);
		            
		            //puertoT.removeAllItems();
		            List<String> puertos = equipo.getPuertos();
		            for (String puerto : puertos) {
		                puertoT.addItem(puerto);
		            }
		               
		        } else {
		            JOptionPane.showMessageDialog(null, "El equipo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    });
			
			panelInferior.add(borrar);
			borrar.setVisible(true);
			
			borrar.addActionListener(e ->{
				try {   
					String codigoSeleccionado = (String) codigoT.getSelectedItem();
			        Equipo equipo = coordinador.getRed().buscarEquipoPorCodigo(codigoSeleccionado);
					if(dudaBorrar(equipo)) {
						coordinador.borrarEquipo(equipo); // recibe un equipo
					}
				} catch(Exception ex) {
			        JOptionPane.showMessageDialog(null, "Error en el formato de entrada: " + ex.getMessage(), "Error de Formato", 
			        		JOptionPane.ERROR_MESSAGE);
					
				}
			});
			
			campo.revalidate();
		    campo.repaint();
		}
		
		private boolean dudaBorrar(Equipo equipo) {
			return false;
		}
}
