package gui.datos.datos;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
				
			JComboBox<String> direccionipCB = new JComboBox<>();
		    direccionipCB.setBounds(50, 240, 350, 20);
		    campo.add(direccionipCB);

		    JButton agregarDireccionIP = new JButton("Agregar Direccion IP");
		    agregarDireccionIP.setBounds(400, 240, 150, 20);
		    campo.add(agregarDireccionIP);

		    agregarDireccionIP.addActionListener(e -> {
		        JFrame frame = new JFrame("Agregar Direccion IP");
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        frame.setLayout(new FlowLayout());

		        JTextField direccionIPField = new JTextField(20);
		        frame.add(direccionIPField);

		        JButton confirmar = new JButton("Confirmar");
		        confirmar.addActionListener(e1 -> {
		            String direccionIP = direccionIPField.getText();
		            direccionipCB.addItem(direccionIP);
		            frame.dispose();
		        });
		        frame.add(confirmar);

		        JButton cancelar = new JButton("Cancelar");
		        cancelar.addActionListener(e1 -> frame.dispose());
		        frame.add(cancelar);

		        frame.pack();
		        frame.setVisible(true);
		    });
			
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
				
			JComboBox<String> puertoCB = new JComboBox<>();
		    puertoCB.setBounds(50, 440, 350, 20);
		    campo.add(puertoCB);

		    JButton agregarPuerto = new JButton("Agregar Puerto");
		    agregarPuerto.setBounds(400, 440, 150, 20);
		    campo.add(agregarPuerto);

		    agregarPuerto.addActionListener(e -> {
		        JFrame frame = new JFrame("Agregar Puerto");
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        frame.setLayout(new FlowLayout());

		        JTextField puertoField = new JTextField(20);
		        frame.add(puertoField);

		        JButton confirmar = new JButton("Confirmar");
		        confirmar.addActionListener(e1 -> {
		            String puerto = puertoField.getText();
		            puertoCB.addItem(puerto);
		            frame.dispose();
		        });
		        frame.add(confirmar);

		        JButton cancelar = new JButton("Cancelar");
		        cancelar.addActionListener(e1 -> frame.dispose());
		        frame.add(cancelar);

		        frame.pack();
		        frame.setVisible(true);
		    });
				
						
			panelInferior.add(cargar);
			cargar.setVisible(true);
			
			cargar.addActionListener(e -> {
				try {
					// Recuperar los datos y crear el objeto equipo
					Equipo equipo = cargarEquipo(codigoT, descripcionT, marcaT, modeloT, direccionipCB, 
							ubicacionT, tipoEquipoT, puertoCB);
	
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
				JComboBox<String> direccionipCB, JTextField ubicacionT, JTextField tipoEquipoT, JComboBox<String> puertosCB) {
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
				
			String[] direccionIPdatos = new String[direccionipCB.getItemCount()];
			for (int i = 0; i < direccionipCB.getItemCount(); i++) {
		        direccionIPdatos[i] = direccionipCB.getItemAt(i);
		    }
			for(String dir: direccionIPdatos) {
				equipo.agregarIp(dir.trim());
			}
			
		    
		    String[] puertoDatos = new String[puertosCB.getItemCount()];
		    for(int i = 0; i <puertosCB.getItemCount(); i++) {
		    	puertoDatos[i] = puertosCB.getItemAt(i);
		    }
			for (String puertoData : puertoDatos) {
				String[] parts = puertoData.split(":");
				String[] tipoPuertoData = parts[0].split(",");
			        
				try {
			        // Crear el TipoPuerto
			        String codigostr = tipoPuertoData[0].trim();
			        String descripcionstr = tipoPuertoData[1].trim();
			        int velocidad = Integer.parseInt(tipoPuertoData[2].trim());

			        TipoPuerto tipoPuerto = new TipoPuerto(codigostr, descripcionstr, velocidad);

			        // Convertir la cantidad y agregar puerto al equipo
			        int cantidad = Integer.parseInt(parts[1].trim());
			        equipo.agregarPuerto(tipoPuerto, cantidad);

			    } catch (NumberFormatException e) {
			        JOptionPane.showMessageDialog(null, "Error en el formato de los datos del puerto.", "Formato Incorrecto", JOptionPane.ERROR_MESSAGE);
			    }
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
							+ "representa el codigo de un tipo de equipo. ","Error: ", JOptionPane.ERROR_MESSAGE);
					return false;
				}	
			}
				
			if(equipo.getDescripcion() == null || !equipo.getDescripcion().isEmpty()) {
				JOptionPane.showMessageDialog(null,"La descripcion no puede estar vacia. ","Error: ",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(equipo.getMarca() == null || equipo.getMarca().isEmpty()) {
				JOptionPane.showMessageDialog(null,"No especifica la marca del equipo. ","Error: ",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(equipo.getModelo() == null || equipo.getModelo().isEmpty()) {
				JOptionPane.showMessageDialog(null,"No especifica el modelo del equipo. ","Error: ",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(equipo.getUbicacion().getCodigo() == null || equipo.getUbicacion().getCodigo().isEmpty() ||
					equipo.getUbicacion().getDescripcion() == null || equipo.getUbicacion().getDescripcion().isEmpty()) {
				JOptionPane.showMessageDialog(null,"El codigo o la descripcion no esta especificado. ","Error: ",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(equipo.getTipoEquipo().getCodigo() == null || equipo.getTipoEquipo().getCodigo().isEmpty() ||
					equipo.getTipoEquipo().getDescripcion() == null || equipo.getTipoEquipo().getDescripcion().isEmpty()) {
				JOptionPane.showMessageDialog(null,"El codigo o la descripcion no esta especificado. ","Error: ",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			for(String dir : equipo.getIPs()) {
				if(!validarIP(dir)) {
					JOptionPane.showMessageDialog(null,"La direccion IP no tiene el formato valido ej: 255.255.255.255. ","Error: ",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			for(String puerto : equipo.getPuertos()) {
				if(!validarPuerto(puerto)) {
					JOptionPane.showMessageDialog(null,"Algunos campos son nulos o estan vacio. ","Error: ",JOptionPane.ERROR_MESSAGE);
					return false;
				}
				
			}
			return true;
		}
		
		private boolean validarIP(String ip) {
			String[] partes = ip.split("\\.");
			if (partes.length != 4) {
		        return false;
		    }
			for (String parte : partes) {
		        // Verifica que la parte sea numérica y no tenga ceros a la izquierda
		        if (!parte.matches("^[0-9]+$") || (parte.length() > 1 && parte.startsWith("0"))) {
		            return false;
		        }
		        
		        int numero = Integer.parseInt(parte);
		        
		        if (numero < 0 || numero > 255) {
		            return false;
		        }
			}
			return true;
		}
		
		private boolean validarPuerto(String puerto) {
			if(puerto == null || puerto.isEmpty()) {
				return false;
			}
			
			String[] partes = puerto.split(",|:");
	        if (partes.length < 4) {
	            return false;
	        }
	        
	        String tipoPuertoCodigo = partes[0];
	        String tipoPuertoDescripcion = partes[1];
	        
	        String velocidadStr = partes[2];
	        int velocidad = Integer.parseInt(velocidadStr.replaceAll("[^0-9]", ""));
	        
	        int cantidad = Integer.parseInt(partes[3]);
	        
	        if (tipoPuertoCodigo.isEmpty() || tipoPuertoDescripcion.isEmpty()) {
	            return false;
	        }
	        if (velocidad <= 0 || cantidad <= 0) {
	            return false;
	        }
		
			return true;
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
					
			JComboBox<String> direccionipT = new JComboBox<>(); 
			direccionipT.setBounds(50, 240, 350, 20);
			campo.add(direccionipT);
			
			JButton editarDireccionesIP = new JButton("Editar Direcciones IP");
		    editarDireccionesIP.setBounds(50, 270, 150, 20);
		    campo.add(editarDireccionesIP);
		    
			// campo 6 (Ubicacion)		
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
			
			editarDireccionesIP.addActionListener(new ActionListener() {
	            
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame frame = new JFrame("Editar Direcciones IP");
		            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		            frame.setSize(400, 200);
		            frame.setLayout(null);
		            JLabel label = new JLabel("Ingrese las direcciones IP separadas por coma:");
		            label.setBounds(50, 50, 300, 20);
		            frame.add(label);

		            JTextField textField = new JTextField();
		            for (int i = 0; i < direccionipT.getItemCount(); i++) {
		                textField.setText(textField.getText() + direccionipT.getItemAt(i) + ",");
		            }
		            textField.setText(textField.getText().replaceAll(",$", ""));
		            textField.setBounds(50, 80, 300, 20);
		            frame.add(textField);

		            JButton aceptar = new JButton("Aceptar");
		            aceptar.setBounds(50, 110, 100, 20);
		            frame.add(aceptar);
		            
		            JButton borrar = new JButton("Borrar");
		            borrar.setBounds(160, 110, 100, 20);
		            frame.add(borrar);
		            
		            JButton cancelar = new JButton("Cancelar");
		            cancelar.setBounds(270, 110, 100, 20);
		            frame.add(cancelar);
		            
		            JTextField textFieldBorrar = new JTextField();
		            textFieldBorrar.setBounds(50, 140, 300, 20);
		            frame.add(textFieldBorrar);
		            
		            aceptar.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    String direccionesIP = textField.getText();
		                    if (direccionesIP != null) {
		                        direccionipT.removeAllItems();
		                        String[] ips = direccionesIP.split(",");
		                        for (String ip : ips) {
		                            direccionipT.addItem(ip);
		                        }
		                    }
		                    frame.dispose();
		                }
		            });
		            
		            borrar.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    String ipBorrar = textFieldBorrar.getText();
		                    String direccionesIP = textField.getText();
		                    String[] ips = direccionesIP.split(",");
		                    String nuevaDireccionIP = "";
		                    for (String ip : ips) {
		                        if (!ip.equals(ipBorrar)) {
		                            nuevaDireccionIP += ip + ",";
		                        }
		                    }
		                    nuevaDireccionIP = nuevaDireccionIP.replaceAll(",$", "");
		                    textField.setText(nuevaDireccionIP);
		                    direccionipT.removeAllItems();
		                    String[] ipsNuevas = nuevaDireccionIP.split(",");
		                    for (String ip : ipsNuevas) {
		                        direccionipT.addItem(ip);
		                    }
		                }
		            });

		            cancelar.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    frame.dispose();
		                }
		            });
		            frame.setVisible(true);
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

		    	    
		    	    equip = new Equipo(codigo, descripcion, marca, modelo, ubicacion, tipoEquipo, true);
		    	    equip.setDireccionIP(direccionesIP);
		    	    equip.setPuertos(puertoDatos);
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
			if(equipo.getDescripcion() == null || !equipo.getDescripcion().isEmpty()) {
				JOptionPane.showMessageDialog(null,"La descripcion no puede estar vacia. ","Error: ",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(equipo.getMarca() == null || equipo.getMarca().isEmpty()) {
				JOptionPane.showMessageDialog(null,"No especifica la marca del equipo. ","Error: ",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(equipo.getModelo() == null || equipo.getModelo().isEmpty()) {
				JOptionPane.showMessageDialog(null,"No especifica el modelo del equipo. ","Error: ",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(equipo.getUbicacion().getCodigo() == null || equipo.getUbicacion().getCodigo().isEmpty() ||
					equipo.getUbicacion().getDescripcion() == null || equipo.getUbicacion().getDescripcion().isEmpty()) {
				JOptionPane.showMessageDialog(null,"El codigo o la descripcion no esta especificado. ","Error: ",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(equipo.getTipoEquipo().getCodigo() == null || equipo.getTipoEquipo().getCodigo().isEmpty() ||
					equipo.getTipoEquipo().getDescripcion() == null || equipo.getTipoEquipo().getDescripcion().isEmpty()) {
				JOptionPane.showMessageDialog(null,"El codigo o la descripcion no esta especificado. ","Error: ",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			for(String dir : equipo.getIPs()) {
				if(!validarIP(dir)) {
					JOptionPane.showMessageDialog(null,"La direccion IP no tiene el formato valido ej: 255.255.255.255. ","Error: ",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			for(String puerto : equipo.getPuertos()) {
				if(!validarPuerto(puerto)) {
					JOptionPane.showMessageDialog(null,"Algunos campos son nulos o estan vacio. ","Error: ",JOptionPane.ERROR_MESSAGE);
					return false;
				}
				
			}
			return true;
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
			/*
			 *  llamar a Calculo desde coordinador para que verifique si
			 *  el equipo tiene una conexion
			 */
			return false;
		}
		
		public void mostrarTabla(Coordinador coordinador) {
			JFrame ventanaEmergente = new JFrame("Lista de Equipos");
		    ventanaEmergente.setSize(800, 400);
		    ventanaEmergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			List<Equipo> listaEquipos = coordinador.listarEquipos();
			String[] nombreColumnas = {"Codigo", "Descripcion","Marca","Modelo","Direccion IP", "Ubicacion","Tipo de Equipo","Puerto","Estado"};
			String[][] dato = new String[listaEquipos.size()][nombreColumnas.length];
			
			for(int i = 0; i < listaEquipos.size(); i++) {
				Equipo equipo = listaEquipos.get(i);
				dato[i][0] = equipo.getCodigo();
				dato[i][1] = equipo.getDescripcion();
				dato[i][2] = equipo.getMarca();
				dato[i][3] = equipo.getModelo();
				dato[i][4] = String.join(", ", equipo.getIPs());
				dato[i][5] = (equipo.getUbicacion() != null) 
					    ? equipo.getUbicacion().getCodigo() + "," + equipo.getUbicacion().getDescripcion() 
					    	    : "Sin Ubicación";
				dato[i][6] = (equipo.getTipoEquipo() != null) 
					    ? equipo.getTipoEquipo().getCodigo() + "," + equipo.getTipoEquipo().getDescripcion() 
					    	    : "Sin Tipo de Equipo";
				dato[i][7] = String.join("; ", equipo.getPuertos());
				dato[i][8] = equipo.getEstado() ? "Activo" : "Inactivo";
				
			}
			JTable tabla = new JTable(dato, nombreColumnas);
		    JScrollPane scrollPane = new JScrollPane(tabla);
		    ventanaEmergente.add(scrollPane);
		    ventanaEmergente.setVisible(true);
		}
}
