import RightNav from "./RightNav";
import LeftNav from "./LeftNav";
import Main from "./Main";
import TopNav from "./TopNav";
import Footer from "./Footer"
import { useParams } from "react-router-dom";

function Content(){

const params=useParams()

    return(
    <>

    
    <TopNav/>
    
 
    <div id="main" className="Content d-flex flex-row " >
    <div id="left" style={{width:"21%"}}>
    <LeftNav/>
    </div>
   <div id="center" style={{width:"60%"}}>
   <Main slug={params}/>

   {/* 
   Author
   related content
      previous and next button
      voting/rating 
    */}
   </div>


    <div id="right"  style={{width:"19%"}}>
    <RightNav/>
    </div>
   
   

    </div>
   <div id="footer" className="bg-light" style={{}}>
<Footer/>
   </div>
    </>
    
    );
    }
    export default Content;