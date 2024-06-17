import SectionTitle from "../components/sectionTitle";
import { FaBeer } from "react-icons/fa";

const Featured = () => {
    return (
        <div className="mt-[12rem]">
            <SectionTitle
                title="Featured Solutions"
            >
            </SectionTitle>
            <div className="mt-15 flex justify-center items-center w-full">
                <div className="grid grid-cols-1 md:grid-cols-3 gap-10 w-5/6 text-center">
                    <div className="flex justify-center items-center flex-col">
                        <span className="inline-block p-5 rounded-full bg-[#222] dark:bg-white flex justify-center items-center">
                            <FaBeer size={30} className={`text-white dark:text-black`} />
                        </span>
                        <h3 className="mt-5">Solution</h3>
                        <p className="mt-5">Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna.</p>
                        <button className="mt-5 py-3 px-6 rounded-md bg-black dark:bg-white text-white dark:text-black font-semibold">
                            Read More
                        </button>
                    </div>
                    <div className="flex justify-center items-center flex-col">
                        <span className="inline-block p-5 rounded-full bg-[#222] dark:bg-white flex justify-center items-center">
                            <FaBeer size={30} className={`text-white dark:text-black`} />
                        </span>
                        <h3 className="mt-5">Solution</h3>
                        <p className="mt-5">Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna.</p>
                        <button className="mt-5 py-3 px-6 rounded-md bg-black dark:bg-white text-white dark:text-black font-semibold">
                            Read More
                        </button>
                    </div>
                    <div className="flex justify-center items-center flex-col">
                        <span className="inline-block p-5 rounded-full bg-[#222] dark:bg-white flex justify-center items-center">
                            <FaBeer size={30} className={`text-white dark:text-black`} />
                        </span>
                        <h3 className="mt-5">Solution</h3>
                        <p className="mt-5">Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna.</p>
                        <button className="mt-5 py-3 px-6 rounded-md bg-black dark:bg-white text-white dark:text-black font-semibold">
                            Read More
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default Featured;