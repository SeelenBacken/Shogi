package Servlets;

//-----------------------Imports start-----------------------

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.graalvm.compiler.nodes.virtual.EnsureVirtualizedNode;

import beans.GameBean;
import dataclasses.AI;
import dataclasses.Figure;
import dataclasses.Player;
import dataclasses.Vector2;
import enums.FigureType;
import enums.TeamType;
import util.FigureSelector;
import util.Vector2Comparator;

//-----------------------Imports end-----------------------

//-----------------------Class (Servlet) start-----------------------

public class Controller extends HttpServlet {
	
	//-----------------------Attributes start-----------------------
	
	private static final long serialVersionUID = 1L;
	private GameBean g;
	
	//-----------------------Attributes end-----------------------
	
	//-----------------------Constructor start-----------------------

    public Controller() {
        super();
    }
    
	//-----------------------Constructor end-----------------------
    
	//-----------------------Methods start-----------------------

    
    /**
	* 
	* The doGet()-Method is called by Http-Requests. It uses and checks the request parameters and then redirects to the following webpage. 
	* 
	* This method is protected.
	* 
	* @throws ServletException
	* @throws IOException
	* 
	* @param	request		This parameter is of type HttpServletRequest.
	* @param	response	This parameter is of type HttpServletResponse.
	* 
	* @return	void
	* 
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//This Attribute holds the Path of the referring page.
		String referer = request.getHeader("referer");
		
		//This If statement uses the referring webpage to distinguish which subsequent action should be executed.
		if(referer.contains("index.jsp")) {
			
			//referer: index.jsp
			
			if(request.getParameter("singleplayer") != null) {								//The player clicked on the button "singleplayer".
				response.sendRedirect(request.getContextPath() + "/jsp/playerMenu.jsp");	//The player will be redirected to playerMenu.jsp.
				
			} else if(request.getParameter("multiplayer") != null) {						//The player clicked on the button "multiplayer".
				response.sendRedirect(request.getContextPath() + "/jsp/Multiplayer.jsp");	//The player will be redirected to Multiplayer.jsp.
				
			} else if(request.getParameter("settings") != null) {							//The player clicked on the button "settings".
				response.sendRedirect(request.getContextPath() + "/jsp/Settings.jsp");		//The player will be redirected to Settings.jsp.
				
			} else {																		//This is the default case.
				response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");			//The player will be redirected to index.jsp.
			}
			
		} else if(referer.contains("playerMenu.jsp")) {
			
			//referer: playerMenu.jsp
			
			//The attribute g is set to a new Instance of GameBean when accessing the playerMenu.jsp.
			g = (GameBean) request.getSession().getAttribute("g");
			
			//Checking the request-parameter "Username" for the invalid value "Com".
			if(request.getParameter("Username").equals("AI")) {
				System.out.println("Your not allowed to name yourself 'AI'.");				//Showing the Error on console -------------------------------------------------------------------------------------------------------------------------------
				response.sendRedirect(request.getContextPath() + "/jsp/playerMenu.jsp");	//if an invalid Username was given, the user will be redirected to the playerMenu.jsp.
				
			} else {
				
				String userName = request.getParameter("Username");
				
				//Setting the Players name-attribute in the GameBean g according to the chosen team.
				if(request.getParameter("PlayerColor").equals("Weiß")) {
					g.getWhite().setName(userName);
					g.setBlack(new AI(TeamType.BLACK));
					
					//The Black team starts. If the black team is the AI, the game starts with a random turn of the AI.
					g.getBlack().doTurn(g.getBoard());
					
				} else {
					g.getBlack().setName(userName);
					g.setWhite(new AI(TeamType.WHITE));
					
				}
				
				//Redirect to the board.jsp if all request-parameters are valid. 
				response.sendRedirect(request.getContextPath() + "/jsp/board.jsp");
				
			}
			
			
		
		} else if(referer.contains("board.jsp")) {
			
			//referer: board.jsp
			
			//The variable player saves the Player-objcet which holds the non-computer-player.
			Player player = (g.getBlack().getName().equals("AI")) ? g.getWhite() : g.getBlack();
	 		
			//Processing the request-parameters. This is the information of which turns were made.
	        //This is the originating position of a figure on the gameboard or in the prison.
			Vector2 hitter = new Vector2(letterToXOrdinate(request.getParameter("Xh").charAt(0)), Integer.parseInt(request.getParameter("Yh")));
			
			//This is the resulting position on the gameboard.
			Vector2 target = new Vector2(letterToXOrdinate(request.getParameter("Xt").charAt(0)), Integer.parseInt(request.getParameter("Yt")));
	 
			
			//This Vector shows which figure has to be upgraded or downgraded.
			String x = String.valueOf(request.getParameter("Xc").charAt(0));
			String y = request.getParameter("Yc");
			
			if((!x.equals("Z") && !y.equals("-1")) && (!x.equals("") && !y.equals(""))) {
				
				Vector2 upgrade = new Vector2(letterToXOrdinate(x.charAt(0)), Integer.parseInt(y));
				
				//The player wants to upgrade a figure.
				//Proove if the figure is in the gameboard (-1 < x,y < 10) and than change type.
				if(upgrade.getX() >= 0 && upgrade.getY() >= 6 && upgrade.getX() < 10 && upgrade.getY() < 10) {
					
					//Proove if the figure that is moved belongs to the non-Com-player.
					if(g.getBoard().getBoard()[upgrade.getX()][upgrade.getY()].getTeam().equals(player.getTeam())) {
						
						if(g.getBoard().getBoard()[upgrade.getX()][upgrade.getY()].canChangeType()) {
							g.getBoard().getBoard()[upgrade.getX()][upgrade.getY()].changeType();
						}
			
					}
					
				}
				
			}
			
			
			//The player wants to move a figure on the gameboard or from the prison into the gameboard. 
			//Prooves if the originating position is in the board.
			if(hitter.getX() >= 0 && hitter.getY() >= 0 && hitter.getX() < 10 && hitter.getY() < 10) {
				
				//Prooves if there is a valid figure at the chosen position.
				if(g.getBoard().getBoard()[hitter.getX()][hitter.getY()] != null) {
					
					//Prooves if the hitter-figure belongs to the non-Com-player.
					if(g.getBoard().getBoard()[hitter.getX()][hitter.getY()].getTeam().equals(player.getTeam())) {
						
						//Prooves if the targeting position is in the board.
						if(target.getX() >= 0 && target.getY() >= 0 && target.getX() < 10 && target.getY() < 10) {
							
							//Prooves if the target-figure doesn't belong to the non-Com-player.
							if(g.getBoard().getBoard()[target.getX()][target.getY()] == null || !g.getBoard().getBoard()[target.getX()][target.getY()].getTeam().equals(player.getTeam())) {
								
								//Prooves if the target is accessible for the figure on the hitter-position.
								if(g.getBoard().getPossibleTurnsFor(hitter).contains(target)) {
				        				
									//Move the figure from target to hitter
				        			g.getBoard().moveOnBoard(hitter, target);
				        			g.getBoard().clearPossibleTurns();
				        			
				        			//Checks if a Team is chackMate 
					        		if(g.getBoard().isCheckMate(g.getBlack().getTeam()) || g.getBoard().ensureFigureTypeForPosition(g.getBoard().getBlackKingPos()) != FigureType.KING) {
					        			
					        			//Checks which player has won.
					        			if (g.getBlack().getName() == "AI") {
											//The non-Com-player has won. Redirect to Win.jsp.
					        				response.sendRedirect(request.getContextPath() + "/jsp/Win.jsp");
											
										} else {
											//The non-Com-player has lost. Redirect to Lost.jsp.
											response.sendRedirect(request.getContextPath() + "/jsp/Lose.jsp");
										}
					        			
									} else if(g.getBoard().isCheckMate(g.getWhite().getTeam()) || g.getBoard().ensureFigureTypeForPosition(g.getBoard().getWhiteKingPos()) != FigureType.KING) {
										
										//Checks which player has won.
										if (g.getWhite().getName() == "AI") {
											//The non-Com-player has won. Redirect to Win.jsp.
											response.sendRedirect(request.getContextPath() + "/jsp/Win.jsp");
											
										} else {
											//The non-Com-player has lost. Redirect to Lost.jsp.
											response.sendRedirect(request.getContextPath() + "/jsp/Lose.jsp");
										}
									
									//If no team is chackMate:
									} else {

										//Do a random Move for the Com-player after the non-Com-Player did one.
										//Get the Player-object of the Com-player.
										Player com = (g.getBlack().getName().equals("AI")) ? g.getBlack() : g.getWhite();
										
										//Does a random move with a random figure. 
										if(com.doTurn(g.getBoard()) == false) {
											//The Com player cannot move anymore. Noone has won.
											response.sendRedirect(request.getContextPath() + "/jsp/pat.jsp");
											
										}
										
									}
					        	
					        	}
								
							}
							
						}
					
					}
				
				}
				
				//If the originating position is below 0, the absolute amount is the index of the figure in a prison.	
        	} else if(hitter.getX() == -1) {
        		
        		//Get the given figure from the non-Com-players prison.

				Figure f = null;
				int index = 0;
				
				//Devides the request by the Teamtype of the non-AI-player. 
				if(player.getTeam().equals(TeamType.BLACK)) {
					
					//Get the first index of the figure with the assotiated number in the prison of the player.
					if (hitter.getY() == -1){
						
						index = g.getBoard().getBlackPrison().indexOf(Figure.getBishop(TeamType.WHITE));
						f = Figure.getBishop(TeamType.BLACK);
						
					} else if(hitter.getY() == -2){
			    		
						index = g.getBoard().getBlackPrison().indexOf(Figure.getTower(TeamType.WHITE));
						f = Figure.getTower(TeamType.BLACK);
						
					} else if(hitter.getY() == -3){
						
						index = g.getBoard().getBlackPrison().indexOf(Figure.getGoldenGeneral(TeamType.WHITE));
						f = Figure.getGoldenGeneral(TeamType.BLACK);
			    		
					} else if(hitter.getY() == -4){
						
						index = g.getBoard().getBlackPrison().indexOf(Figure.getSilverGeneral(TeamType.WHITE));
						f = Figure.getBishop(TeamType.BLACK);
							
					} else if(hitter.getY() == -5){
						
						index = g.getBoard().getBlackPrison().indexOf(Figure.getKnight(TeamType.WHITE));
						f = Figure.getKnight(TeamType.BLACK);
						
			    	} else if(hitter.getY() == -6){
			    		
			    		index = g.getBoard().getBlackPrison().indexOf(Figure.getPawn(TeamType.WHITE));
		    			f = Figure.getPawn(TeamType.BLACK);
			    		
			    	} else if(hitter.getY() == -7){
			    		
			    		index = g.getBoard().getBlackPrison().indexOf(Figure.getLance(TeamType.WHITE));
		    			f = Figure.getLance(TeamType.BLACK);
			    		
			    	}
					
					//Prooves if the given Y-value of the hitter is between -7 and -1.
					if(f != null) {
						
						//Prooves if the given target is on the gameboard.
						if(target.getX() >= 0 && target.getY() >= 0 && target.getX() < 10 && target.getY() < 10) {
							
							//Prooves if the given target has no figure on it.
							if(g.getBoard().getBoard()[target.getX()][target.getY()] == null) {
								
								//Prooves if the move would directly check the Com-Player
								if(!g.getBoard().causesCheck(target, f)) {
									
									//Removes the figure from the prison if the target-position is valid. 
									g.getBoard().getBlackPrison().remove(index);
									
									//Place the figure on the target.
									g.getBoard().getBoard()[target.getX()][target.getY()] = f;
				        			
								}
								
							} 
							
						}
						
					}
					
				} else if(player.getTeam().equals(TeamType.WHITE)) {
					
					//Get the first index of the figure with the assotiated number in the prison of the player.
					if (hitter.getY() == -1){
						
						index = g.getBoard().getWhitePrison().indexOf(Figure.getBishop(TeamType.BLACK));
						f = Figure.getBishop(TeamType.WHITE);
						
					} else if(hitter.getY() == -2){
			    		
						index = g.getBoard().getWhitePrison().indexOf(Figure.getTower(TeamType.BLACK));
						f = Figure.getTower(TeamType.WHITE);
						
					} else if(hitter.getY() == -3){
						
						index = g.getBoard().getWhitePrison().indexOf(Figure.getGoldenGeneral(TeamType.BLACK));
						f = Figure.getGoldenGeneral(TeamType.WHITE);
			    		
					} else if(hitter.getY() == -4){
						
						index = g.getBoard().getWhitePrison().indexOf(Figure.getSilverGeneral(TeamType.BLACK));
						f = Figure.getBishop(TeamType.WHITE);
							
					} else if(hitter.getY() == -5){
						
						index = g.getBoard().getWhitePrison().indexOf(Figure.getKnight(TeamType.BLACK));
						f = Figure.getKnight(TeamType.WHITE);
						
			    	} else if(hitter.getY() == -6){
			    		
			    		index = g.getBoard().getWhitePrison().indexOf(Figure.getPawn(TeamType.BLACK));
		    			f = Figure.getPawn(TeamType.WHITE);
			    		
			    	} else if(hitter.getY() == -7){
			    		
			    		index = g.getBoard().getWhitePrison().indexOf(Figure.getLance(TeamType.BLACK));
		    			f = Figure.getLance(TeamType.WHITE);
			    		
			    	}
					
					//Prooves if the given Y-value of the hitter is between -7 and -1.
					if(f != null) {
						
						//Prooves if the given target is on the gameboard.
						if(target.getX() >= 0 && target.getY() >= 0 && target.getX() < 10 && target.getY() < 10) {
							
							//Prooves if the given target has no figure on it.
							if(g.getBoard().getBoard()[target.getX()][target.getY()] == null) {
								
								//Prooves if the move would directly check the Com-Player
								if(!g.getBoard().causesCheck(target, f)) {
									
									//Removes the figure from the prison if the target-position is valid. 
									g.getBoard().getWhitePrison().remove(index);
									
									//Place the figure on the target.
									g.getBoard().getBoard()[target.getX()][target.getY()] = f;
				        			
								}
								
							} 
							
						}
						
					}
					
				}
        		
        	}
			
			//Redirect to the board.jsp.
			response.sendRedirect(request.getContextPath() + "/jsp/board.jsp");
			
		} else if(referer.contains("Win.jsp") | referer.contains("Lose.jsp")) {
			
			//referer: Win.jsp or Lose.jsp
			
			if(request.getParameter("playAgain") != null) {									//The Player pressed the button "playAgain".	
				response.sendRedirect(request.getContextPath() + "/jsp/playerMenu.jsp");	//The player will be redirected to playerMenu.jsp.
				
			} if(request.getParameter("startPage") != null) {								//The Player pressed the button "startpage".
				response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");			//The player will be redirected to index.jsp.
			}
			
		}
		
	}
	
	
	
	
	/**
	* 
	* Returns the matching number to the given character by the alphabetical pattern:
	* 
	* A -> 0
	* B -> 1
	* C -> 2
	* ...
	* I -> 8
	* 
	* This method is private and static.
	*
	* @param	c	The Char that will be matched to the alphabetical pattern. Legal values are alphabetical in between A and I.
	* 
	* @return		Returns an Integer-value alphabetically matching to the @param c. The default value is -1. If the @param is illegal, the default-value is returned.
	* 
	*/
	private static int letterToXOrdinate(char c) {
		
		switch (c) {
			case 'A': return 0;
			
			case 'B': return 1;
			
			case 'C': return 2;
			
			case 'D': return 3;
			
			case 'E': return 4;
			
			case 'F': return 5;
			
			case 'G': return 6;
			
			case 'H': return 7;
			
			case 'I': return 8;
			
			default: return -1;	
		}
	
	}	
	
	//-----------------------Methods end-----------------------

}

//-----------------------Class (Servlet) end-----------------------
