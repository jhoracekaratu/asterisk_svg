import { useEffect, useState,useContext } from "react";
import styles from "./Main.module.css";
import { UserContext } from "./UserContext";
import "highlight.js/styles/github.css";
import hljs from "highlight.js";


function Main(props){
    const userContext = useContext(UserContext);
    const fetchPostData=()=>{
        const url="http://localhost:8082/api/postbyslug/"+props.slug.slug
        fetch(url, {method:'GET', 
headers: {'Authorization': 'Basic ' + btoa('admin:admin')}})
            .then((response)=>{
               return response.json()
            })
            .then((post)=>{
                console.log(post)
                setPost(post.post)
                userContext.setPostcontext(post.slugs)
                userContext.setRelatedcontext(post.related)
            }).catch((error) => console.log(error));

    }
    const test=()=>{
    
    }

    const [post,setPost]=useState([])
    useEffect(()=>{

    test()
  
        fetchPostData()
       
    },[])
        



    return(
    <>
    <div className={`Main`} >

    <div className={styles.content}>

     <h2 className={`${styles.header} mb-4`}>{post.title}</h2>


   {
   
    post.title!=undefined &&  Array.prototype.map.call(post.postParts, part => {
        
    
            return (
            <div className={`${styles.pills}`} key={part.id} >
            <h3 className={`${styles.subtitle} mb-4`}>{part.sub_title}</h3>
            <p className={`${styles.body} mb-4`} dangerouslySetInnerHTML={{ __html: part.sub_content }}></p>
     </div>
                
            );
    
    }
)

   
    
}

{document.querySelectorAll("pre ").forEach((el) => {
        hljs.highlightElement(el);
        console.log(el)
      })}
      {/* {hljs.initHighlightingOnLoad()} */}

    </div>
    
    </div>
        
    </>
    
    );
    }
    export default Main;
