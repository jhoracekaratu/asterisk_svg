// import RightNav from "./RightNav";
import RightNav from "./KRightNav";
// import LeftNav from "./LeftNav";
import LeftNav from "./KLeftNav";
// import Main from "./Main";
import Main from "./KMain";
import TopNav from './KTopNav';
import { Outlet } from "react-router-dom";

function Content(){
    return(
    <>
     <TopNav/>
    <div className="Content" >
    <LeftNav/>
    <Outlet>
    {/* <Main/> */}
    </Outlet>
    <RightNav/>
  

    </div>
   
    </>
    
    );
    }
    export default Content;