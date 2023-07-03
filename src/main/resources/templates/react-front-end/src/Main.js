import { useEffect, useState,useContext } from "react";
import styles from "./Main.module.css";
import { UserContext } from "./UserContext";
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

    {/* let content be in fom of pill form from the start.
     From admin, put markers to seperate as pills with zebra coloring */}

     {/* style for topic here */}
     <h1 className={`${styles.header}`}>{post.title}</h1>

     {/* seperate pills db should save each of the elements seperately
     title
     list of pills and their headings
     
     list of pills, java will seperate the headeings
     date
     hit count
     author
     status, published or not
     updates history
     
     the site has to be very dynamic. I have created it rather than wordpress so i will customize it deep. kafka and so om. But first, sass and webpack perfection
      */}

    
     
{/* create a loop to itereate the sub title object and map to h2s */}

   {
   
    post.title!=undefined &&  Array.prototype.map.call(post.postParts, part => {
        
    
            return (
            <div className={`${styles.pills}`} key={part.id} >
            <h2 className={`${styles.header}`}>{part.sub_title}</h2>
            <p className={`${styles.body}`} dangerouslySetInnerHTML={{ __html: part.sub_content }}></p>
     </div>
                
            );
    
    }
)
    
}
    
    </div>
    
    </div>
        
    </>
    
    );
    }
    export default Main;
