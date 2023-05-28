import { useEffect, useState } from "react";
import styles from "./Main.module.css"
function Main(){
    const fetchPostData=()=>{
        const url="http://localhost:8082/post/1"
        fetch(url, {method:'GET', 
headers: {'Authorization': 'Basic ' + btoa('admin:admin')}})
            .then((response)=>{
               return response.json()
            })
            .then((post)=>{
            console.log(post)
                setPost(post)
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
     <h1 className={`${styles.header}`}>{post.data}</h1>

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

     <div className={`${styles.pills}`} >
     

     <h2 className={`${styles.header}`}>thread types</h2>

    <p className={`${styles.body}`}>Threads assist to handle more than one task simulataneously.</p>
     </div>
    
    </div>
    
    </div>
        
    </>
    
    );
    }
    export default Main;
