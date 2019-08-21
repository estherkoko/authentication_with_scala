package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.Json
import repositories.DataRepository

@Singleton
class ApiController @Inject()(cc: ControllerComponents, dataRepository: DataRepository) extends AbstractController(cc){
    
    def ping = Action { implicit request =>
        Ok("Hello, Scala!")
    }

    //get a single post
    def getPost(postId: Int) = Action {implicit request => 
        dataRepository.getPost(postId) map { post =>
            Ok(Json.toJson(post))
        } getOrElse NotFound  
    }

    //get comments for a post
    def getComments(postId: Int) = Action { implicit request =>
        Ok(Json.toJson(dataRepository.getComments(postId)))
    }
}